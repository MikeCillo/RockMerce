package DataTier.RockMerceDAO.Guitar;

import DataTier.TestDbUtil;
import LogicTier.Entità.Guitar;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class GuitarDAOIntegrationTest {

    @Before
    public void setup() throws Exception {
        TestDbUtil.initH2();
    }

    @Test
    public void insertRetrieveUpdateDelete_and_decrement_check() throws Exception {
        GuitarDAO dao = new GuitarDAO();

        // insert new guitar using DAO method
        Guitar g = new Guitar();
        g.setName("G-Insert");
        g.setPrice(200.0);
        g.setProducer("Prod");
        g.setCategory("electric");
        g.setDisponibility(3);
        g.setSound("https://www.youtube.com/watch?v=abcdefg12345");
        g.setImage("img.png");
        g.setDescription("desc");
        g.setVisibility("yes");
        g.setColor("red");

        boolean inserted = dao.doInsertNewGuitar(g);
        assertTrue(inserted);

        // admindoRetrieveGuitars should contain at least one
        assertTrue(dao.admindoRetrieveGuitars().size() >= 1);

        // get id of last inserted guitar
        int lastId = -1;
        try (Connection con = DataTier.DBCONNECTION.DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT id FROM Guitar WHERE name = ? ORDER BY id DESC LIMIT 1")) {
            ps.setString(1, "G-Insert");
            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next());
                lastId = rs.getInt(1);
            }
        }
        assertTrue(lastId > 0);

        // retrieve by id
        Guitar fetched = dao.doRetrieveGuitarById(lastId);
        assertNotNull(fetched);
        assertEquals("G-Insert", fetched.getName());

        // decrement path: disponibility >=2 -> should reduce by 1
        fetched.setDisponibility(3);
        fetched.setId(lastId);
        dao.decrementGuitar(fetched);
        Guitar afterDec = dao.doRetrieveGuitarById(lastId);
        assertEquals(2, afterDec.getDisponibility());

        // decrement when disponibility ==1 -> becomes 0 and visibility no
        // set directly in DB
        try (Connection con = DataTier.DBCONNECTION.DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE Guitar SET disponibility=?, visibility=? WHERE id=?")) {
            ps.setInt(1, 1);
            ps.setString(2, "yes");
            ps.setInt(3, lastId);
            ps.executeUpdate();
        }
        Guitar one = dao.doRetrieveGuitarById(lastId);
        dao.decrementGuitar(one);
        Guitar afterDec2 = dao.doRetrieveGuitarById(lastId);
        assertEquals(0, afterDec2.getDisponibility());
        assertEquals("no", afterDec2.getVisibility());

        // checkGuitar true when disponibility >=1 and visibility yes
        // insert another guitar with disponibility 2
        try (Connection con = DataTier.DBCONNECTION.DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Guitar (name,price,producer,category,disponibility,sound,image,description,visibility,color) values ('G-Chk',150,'P','electric',2,'https://www.youtube.com/watch?v=123','img.png','d','yes','blue')", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id2 = rs.getInt(1);
                    Guitar checkG = dao.doRetrieveGuitarById(id2);
                    assertTrue(dao.checkGuitar(checkG));

                    // now set disponibility to 0 and check becomes false
                    try (PreparedStatement ps2 = con.prepareStatement("UPDATE Guitar SET disponibility=? WHERE id=?")) {
                        ps2.setInt(1, 0);
                        ps2.setInt(2, id2);
                        ps2.executeUpdate();
                    }
                    Guitar checkG2 = dao.doRetrieveGuitarById(id2);
                    assertFalse(dao.checkGuitar(checkG2));

                    // clean up delete
                    dao.deleteGuitar(checkG2);
                }
            }
        }

        // findGuitar: insert a unique guitar and find it
        try (Connection con = DataTier.DBCONNECTION.DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Guitar (name,price,producer,category,disponibility,sound,image,description,visibility,color) values ('G-Find',300,'FProd','classic',1,'https://www.youtube.com/watch?v=xyz','img.jpeg','d','yes','green')", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id3 = rs.getInt(1);
                    Guitar toFind = new Guitar();
                    toFind.setName("G-Find");
                    toFind.setProducer("FProd");
                    toFind.setCategory("classic");
                    toFind.setColor("green");
                    Guitar found = dao.findGuitar(toFind);
                    assertNotNull(found);
                    assertEquals("G-Find", found.getName());

                    // update
                    found.setPrice(350.0);
                    found.setDescription("updated");
                    dao.doUpdateGuitar(found);
                    Guitar updated = dao.doRetrieveGuitarById(id3);
                    assertEquals(350.0, updated.getPrice(), 0.001);

                    // delete
                    dao.deleteGuitar(updated);
                    assertNull(dao.doRetrieveGuitarById(id3));
                }
            }
        }
    }
}

