package test.utils;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;

public class TestDatabaseUtil {

    public static void init() throws Exception {
        // Configura una DataSource H2 in-memory e la inietta nella classe DbConnection
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        p.setDriverClassName("org.h2.Driver");
        p.setUsername("sa");
        p.setPassword("");
        p.setMaxActive(10);

        DataSource ds = new DataSource();
        ds.setPoolProperties(p);


        Class<?> dbConnClass = Class.forName("DataTier.DBCONNECTION.DbConnection");
        Field datasourceField = dbConnClass.getDeclaredField("datasource");
        datasourceField.setAccessible(true);
        datasourceField.set(null, ds);

        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS CreditCard (id INT AUTO_INCREMENT PRIMARY KEY, cardNumber VARCHAR(255), owner VARCHAR(255), expireDate VARCHAR(50), cvv INT)");

            st.execute("CREATE TABLE IF NOT EXISTS Cart (id INT AUTO_INCREMENT PRIMARY KEY, tempTotal DOUBLE, numGuitars INT)");

            st.execute("CREATE TABLE IF NOT EXISTS Customer (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), name VARCHAR(255), surname VARCHAR(255), password VARCHAR(255), phone VARCHAR(50), country VARCHAR(100), city VARCHAR(100), address VARCHAR(255), cardId INT, cartId INT)");

            st.execute("CREATE TABLE IF NOT EXISTS Guitar (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DOUBLE, producer VARCHAR(255), category VARCHAR(255), disponibility INT, sound VARCHAR(255), image VARCHAR(255), description VARCHAR(1000), visibility VARCHAR(10), color VARCHAR(100))");

            st.execute("CREATE TABLE IF NOT EXISTS CartContent (cart INT, id INT, quantity INT, price DOUBLE)");

            st.execute("CREATE TABLE IF NOT EXISTS Checkout (id INT AUTO_INCREMENT PRIMARY KEY, total DOUBLE, sendDate VARCHAR(50), orderDate VARCHAR(50), cartId INT)");

            st.execute("CREATE TABLE IF NOT EXISTS CheckoutContent (id INT AUTO_INCREMENT PRIMARY KEY, idGuitar INT, name VARCHAR(255), quantity INT, price DOUBLE, producer VARCHAR(255), category VARCHAR(255), color VARCHAR(100), idCheckout INT)");
        }
    }

    public static void cleanAll() throws Exception {
        // Pulisce i dati tra i test
        Class<?> dbConnClass = Class.forName("DataTier.DBCONNECTION.DbConnection");
        Field datasourceField = dbConnClass.getDeclaredField("datasource");
        datasourceField.setAccessible(true);
        DataSource ds = (DataSource) datasourceField.get(null);

        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            st.execute("DELETE FROM CheckoutContent");
            st.execute("DELETE FROM Checkout");
            st.execute("DELETE FROM CartContent");
            st.execute("DELETE FROM Customer");
            st.execute("DELETE FROM CreditCard");
            st.execute("DELETE FROM Cart");
            st.execute("DELETE FROM Guitar");
        }
    }
}

