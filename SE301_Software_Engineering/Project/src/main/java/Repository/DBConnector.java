package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static DBConnector dbconnector;
    private static Connection connection;
    private final static String DB_URL = "";
    private final static String DB_USERNAME = "";
    private final static String DB_PASSWORD = "";

    private DBConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    static DBConnector getInstance() {
        if (dbconnector == null) {
            dbconnector = new DBConnector();
        }
        return dbconnector;
    }

    static Connection getConnection() {
        return connection;
    }

    static String getDB_USERNAME() {
        return DB_USERNAME;
    }

    static String getDB_URL() {
        return DB_URL;
    }

    static String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

}
