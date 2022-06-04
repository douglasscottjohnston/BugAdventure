import Model.Database.Connect;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class SQLTests {

    @Test
    public void connectionNotNullTest() {
        Connection conn = null;
        try {
            System.out.println(System.getProperty("user.dir"));
            String url = "jdbc:sqlite:monsters.sqlite";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to database established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(conn);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    public void connectSpiderResultSetNotNullTest() {
        assertNotNull(Connect.retrieveRow("Spider"));
        Connect.closeConnection();
    }

    @Test
    public void connectCentipedeResultSetNotNullTest() {
        assertNotNull(Connect.retrieveRow("Centipede"));
        Connect.closeConnection();
    }

    @Test
    public void connectMaggotResultSetNotNullTest() {
        assertNotNull(Connect.retrieveRow("Maggot"));
        Connect.closeConnection();
    }

    @Test
    public void connectMossterResultSetNotNullTest() {
        assertNotNull(Connect.retrieveRow("Mosster"));
        Connect.closeConnection();
    }
}