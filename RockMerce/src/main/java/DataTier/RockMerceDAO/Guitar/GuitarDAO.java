package DataTier.RockMerceDAO.Guitar;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Guitar;


import java.sql.*;
import java.util.ArrayList;


public class GuitarDAO {

    public ArrayList<Guitar> doRetrieveGuitars() {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Guitar WHERE visibility=?");

            ps.setString(1, "yes");

            ResultSet rs = ps.executeQuery();
            ArrayList<Guitar> guitars = new ArrayList<>();
            while (rs.next()) {
                Guitar guitar = new Guitar();
                guitar.setId(rs.getInt(1));
                guitar.setName(rs.getString(2));
                guitar.setPrice(rs.getDouble(3));
                guitar.setProducer(rs.getString(4));
                guitar.setCategory(rs.getString(5));
                guitar.setDisponibility(rs.getInt(6));
                guitar.setSound(rs.getString(7));
                guitar.setImage(rs.getString(8));
                guitar.setDescription(rs.getString(9));
                guitar.setVisibility(rs.getString(10));
                guitar.setColor(rs.getString(11));
                guitars.add(guitar);
            }
            return guitars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Guitar> doRetrieveGuitarsByCategory(String category) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Guitar where category=? and visibility=? ");

            ps.setString(1, category);
            ps.setString(2, "yes");

            ResultSet rs = ps.executeQuery();
            ArrayList<Guitar> guitars = new ArrayList<>();
            while (rs.next()) {
                Guitar guitar = new Guitar();
                guitar.setId(rs.getInt(1));
                guitar.setName(rs.getString(2));
                guitar.setPrice(rs.getDouble(3));
                guitar.setProducer(rs.getString(4));
                guitar.setCategory(rs.getString(5));
                guitar.setDisponibility(rs.getInt(6));
                guitar.setSound(rs.getString(7));
                guitar.setImage(rs.getString(8));
                guitar.setDescription(rs.getString(9));
                guitar.setVisibility(rs.getString(10));
                guitar.setColor(rs.getString(11));
                guitars.add(guitar);
            }
            return guitars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Guitar doRetrieveGuitarById(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Guitar WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Guitar guitar = new Guitar();
                guitar.setId(rs.getInt(1));
                guitar.setName(rs.getString(2));
                guitar.setPrice(rs.getFloat(3));
                guitar.setProducer(rs.getString(4));
                guitar.setCategory(rs.getString(5));
                guitar.setDisponibility(rs.getInt(6));
                guitar.setSound(rs.getString(7));
                guitar.setImage(rs.getString(8));
                guitar.setDescription(rs.getString(9));
                guitar.setVisibility(rs.getString(10));
                guitar.setColor(rs.getString(11));
                return guitar;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void decrementGuitar(Guitar guitar) {
        try (Connection con = DbConnection.getConnection()) {

            if (guitar.getDisponibility() >= 2) {

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE Guitar SET disponibility=?  WHERE id=?  ");

                ps.setInt(1, guitar.getDisponibility() - 1);
                ps.setInt(2, guitar.getId());


                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Decrement error.");
                }

            } else if (guitar.getDisponibility() == 0) {

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE Guitar SET visibility=?  WHERE id=? ");

                ps.setString(1, "no");
                ps.setInt(2, guitar.getId());


                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Decrement error.");
                }
            } else if (guitar.getDisponibility() == 1) {
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE Guitar SET disponibility=?,visibility=?  WHERE id=? ");

                ps.setInt(1, 0);
                ps.setString(2, "no");
                ps.setInt(3, guitar.getId());


                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Decrement error.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Boolean checkGuitar(Guitar guitar) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Guitar WHERE name=? and producer=? and category=? and visibility=?  ");

            ps.setString(1, guitar.getName());
            ps.setString(2, guitar.getProducer());
            ps.setString(3, guitar.getCategory());
            ps.setString(4, "yes");

            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                int disp = rs.getInt(6);
                if (disp >= 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Guitar findGuitar(Guitar guitar) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Guitar WHERE name=? and producer=? and category=? and color=?");

            ps.setString(1, guitar.getName());
            ps.setString(2, guitar.getProducer());
            ps.setString(3, guitar.getCategory());
            ps.setString(4, guitar.getColor());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Guitar dbGuitar = new Guitar();
                dbGuitar.setId(rs.getInt(1));
                dbGuitar.setName(rs.getString(2));
                Float price = rs.getFloat(3);
                dbGuitar.setPrice(price);
                dbGuitar.setProducer(rs.getString(4));
                dbGuitar.setCategory(rs.getString(5));
                dbGuitar.setDisponibility(rs.getInt(6));
                dbGuitar.setSound(rs.getString(7));
                dbGuitar.setImage(rs.getString(8));
                dbGuitar.setDescription(rs.getString(9));
                dbGuitar.setVisibility(rs.getString(10));
                dbGuitar.setColor(rs.getString(11));
                return dbGuitar;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void deleteGuitar(Guitar guitar) {
        try (Connection con = DbConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Guitar " +
                            "WHERE id =?");

            ps.setInt(1, guitar.getId());


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE FAILED");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doUpdateGuitar(Guitar guitar){

        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement ("update Guitar set name=?,price=?,producer=?,category=?,disponibility=?,sound=?,description=?,visibility=?,color=? where id=?");

            ps.setString(1,guitar.getName());
            ps.setDouble(2,guitar.getPrice());
            ps.setString(3,guitar.getProducer());
            ps.setString(4,guitar.getCategory());
            ps.setInt(5,guitar.getDisponibility());
            ps.setString(6,guitar.getSound());
            ps.setString(7,guitar.getDescription());
            ps.setString(8,guitar.getVisibility());
            ps.setString(9,guitar.getColor());
            ps.setInt(10,guitar.getId());


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE FAILED");
            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Guitar> AdmdoRetrieveGuitars() {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Guitar  ");


            ResultSet rs = ps.executeQuery();
            ArrayList<Guitar> guitars = new ArrayList<>();
            while (rs.next()) {
                Guitar guitar = new Guitar();
                guitar.setId(rs.getInt(1));
                guitar.setName(rs.getString(2));
                guitar.setPrice(rs.getDouble(3));
                guitar.setProducer(rs.getString(4));
                guitar.setCategory(rs.getString(5));
                guitar.setDisponibility(rs.getInt(6));
                guitar.setSound(rs.getString(7));
                guitar.setImage(rs.getString(8));
                guitar.setDescription(rs.getString(9));
                guitar.setVisibility(rs.getString(10));
                guitar.setColor(rs.getString(11));
                guitars.add(guitar);
            }
            return guitars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doInsertNewGuitar(Guitar guitar){

        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement ("INSERT into Guitar (name,price,producer,category,disponibility,sound,image,description,visibility,color) values (?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,guitar.getName());
            ps.setDouble(2,guitar.getPrice());
            ps.setString(3,guitar.getProducer());
            ps.setString(4,guitar.getCategory());
            ps.setInt(5,guitar.getDisponibility());
            ps.setString(6,guitar.getSound());
            ps.setString(7,guitar.getImage());
            ps.setString(8,guitar.getDescription());
            ps.setString(9,guitar.getVisibility());
            ps.setString(10,guitar.getColor());


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE FAILED");
            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
