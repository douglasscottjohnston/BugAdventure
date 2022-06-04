import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

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
        assertTrue(conn != null);
    }


}
