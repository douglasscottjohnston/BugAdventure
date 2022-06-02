package Model.Database;

import java.sql.*;

public class Connect {

    public static Connection connect() {
        Connection conn = null;
        try {
            System.out.println(System.getProperty("user.dir"));
            String url = "jdbc:sqlite::memory:./identifier.sqlite";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to database established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }

    public static ResultSet retrieveRow(final String theName) {
        final String STATEMENT = "SELECT * WHERE name = ?";
        ResultSet result = null;

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(STATEMENT);
            stmt.setString(1, theName);
            result = stmt.executeQuery();
            conn.close();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
        return result;
    }
}
