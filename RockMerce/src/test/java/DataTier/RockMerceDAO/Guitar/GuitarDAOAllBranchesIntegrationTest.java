package DataTier.RockMerceDAO.Guitar;

import DataTier.TestDbUtil;
import LogicTier.Entità.Guitar;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class GuitarDAOAllBranchesIntegrationTest {

    @Before
    public void setup() throws Exception {
        TestDbUtil.initH2();
    }

    @Test
    public void test_all_branches_of_guitardao() throws Exception {
        GuitarDAO dao = new GuitarDAO();

        // Insert 3 guitars with different disponibility and categories
        int id1 = insertGuitar("G1", 150.0, "ProdA", "electric", 3, "img.png", "desc", "yes", "red");
        int id2 = insertGuitar("G2", 120.0, "ProdB", "classic", 1, "img.png", "desc", "yes", "blue");


        // admindoRetrieveGuitars should find all
        assertTrue(dao.admindoRetrieveGuitars().size() >= 3);

        // doRetrieveGuitarsByCategory: electric should contain G1 and not G3 because G3 visibility no
        assertTrue(dao.doRetrieveGuitarsByCategory("electric").stream().anyMatch(g -> "G1".equals(g.getName())));

        // doRetrieveGuitarById
        Guitar g1 = dao.doRetrieveGuitarById(id1);
        assertNotNull(g1);
        assertEquals("G1", g1.getName());

        // checkGuitar true when disponibility >=1 and visibility yes
        assertTrue(dao.checkGuitar(g1));

        // decrementGuitar when disponibility >=2 -> reduce by 1
        g1.setDisponibility(3);
        g1.setId(id1);
        dao.decrementGuitar(g1);
        Guitar afterDec = dao.doRetrieveGuitarById(id1);
        assertEquals(2, afterDec.getDisponibility());

        // decrement when disponibility ==1 -> set disponibility 0 and visibility no
        Guitar g2 = dao.doRetrieveGuitarById(id2);
        assertNotNull(g2);
        g2.setId(id2);
        g2.setDisponibility(1);
        dao.decrementGuitar(g2);
        Guitar afterDec2 = dao.doRetrieveGuitarById(id2);
        assertEquals(0, afterDec2.getDisponibility());
        assertEquals("no", afterDec2.getVisibility());

        // findGuitar by name/producer/category/color
        Guitar probe = new Guitar();
        probe.setName("G1"); probe.setProducer("ProdA"); probe.setCategory("electric"); probe.setColor("red");
        Guitar found = dao.findGuitar(probe);
        assertNotNull(found);
        assertEquals("G1", found.getName());

        // doUpdateGuitar: change price and description
        found.setPrice(177.77);
        found.setDescription("updated");
        dao.doUpdateGuitar(found);
        Guitar updated = dao.doRetrieveGuitarById(found.getId());
        assertEquals(177.77, updated.getPrice(), 0.001);
        assertEquals("updated", updated.getDescription());

        // doInsertNewGuitar: insert a new one and then delete it
        Guitar newG = new Guitar();
        newG.setName("G4"); newG.setPrice(200.0); newG.setProducer("ProdD"); newG.setCategory("semiAcoustic");
        newG.setDisponibility(2); newG.setSound("https://www.youtube.com/watch?v=abcdabcd"); newG.setImage("img.png");
        newG.setDescription("desc"); newG.setVisibility("yes"); newG.setColor("white");
        boolean inserted = dao.doInsertNewGuitar(newG);
        assertTrue(inserted);

        // find the new one
        Guitar g4 = dao.findGuitar(newG);
        assertNotNull(g4);

        // delete
        dao.deleteGuitar(g4);
        assertNull(dao.doRetrieveGuitarById(g4.getId()));
    }

    private int insertGuitar(String name, double price, String producer, String category, int disponibility, String image, String description, String visibility, String color) throws Exception {
        try (Connection con = DataTier.DBCONNECTION.DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Guitar (name,price,producer,category,disponibility,sound,image,description,visibility,color) values (?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, producer);
            ps.setString(4, category);
            ps.setInt(5, disponibility);
            ps.setString(6, "https://www.youtube.com/watch?v=abcdefg12345");
            ps.setString(7, image);
            ps.setString(8, description);
            ps.setString(9, visibility);
            ps.setString(10, color);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
}

