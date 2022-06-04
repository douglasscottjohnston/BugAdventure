package Model.Database;

import java.sql.*;

public class Connect {
    private static Connection myConn;

    public static void connect() {
        try {
            String url = "jdbc:sqlite:monsters.sqlite";
            myConn = DriverManager.getConnection(url);

            System.out.println("Connection to database established");
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet retrieveRow(final String theName) {
        final String STATEMENT = "SELECT * FROM monsters WHERE name = ?";
        ResultSet result = null;

        try {
            connect();
            PreparedStatement stmt = myConn.prepareStatement(STATEMENT);
            stmt.setString(1, theName);
            result = stmt.executeQuery();
        } catch (final SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public static void closeConnection() {
        try {
            myConn.close();
        } catch (final SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
