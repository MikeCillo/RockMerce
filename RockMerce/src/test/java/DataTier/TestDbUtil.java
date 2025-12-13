package DataTier;

import DataTier.DBCONNECTION.DbConnection;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;

public class TestDbUtil {

    public static void initH2() throws Exception {
        // Crea e configura datasource H2
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:h2:mem:rockmerce;DB_CLOSE_DELAY=-1;MODE=MySQL");
        p.setDriverClassName("org.h2.Driver");
        p.setUsername("sa");
        p.setPassword("");
        p.setMaxActive(10);
        p.setInitialSize(1);
        DataSource ds = new DataSource();
        ds.setPoolProperties(p);

        // Imposta il datasource privato nella classe DbConnection via reflection
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, ds);

        // Crea le tabelle necessarie
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            st.execute("CREATE TABLE Cart (id INT AUTO_INCREMENT PRIMARY KEY, tempTotal DOUBLE, numGuitars INT);");
            st.execute("CREATE TABLE CreditCard (id INT AUTO_INCREMENT PRIMARY KEY, cardNumber VARCHAR(255), owner VARCHAR(255), expireDate VARCHAR(10), cvv INT);");
            st.execute("CREATE TABLE Customer (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), name VARCHAR(255), surname VARCHAR(255), password VARCHAR(255), phone VARCHAR(50), country VARCHAR(255), city VARCHAR(255), address VARCHAR(255), cardId INT, cartId INT);");
            st.execute("CREATE TABLE Guitar (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DOUBLE, producer VARCHAR(255), category VARCHAR(255), disponibility INT, sound VARCHAR(255), image VARCHAR(255), description VARCHAR(1024), visibility VARCHAR(10), color VARCHAR(50));");
            st.execute("CREATE TABLE CartContent (cart INT, id INT, quantity INT, price DOUBLE);");
            st.execute("CREATE TABLE Checkout (id INT AUTO_INCREMENT PRIMARY KEY, cartId INT, sendDate TIMESTAMP, orderDate TIMESTAMP, totalPrice DOUBLE);");
            st.execute("CREATE TABLE CheckoutContent (checkoutId INT, id INT, quantity INT, price DOUBLE);");
        }
    }

}
