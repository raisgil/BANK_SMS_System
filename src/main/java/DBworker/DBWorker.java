package DBworker;

import java.sql.*;

public class DBWorker {

    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USERNAME = "base";
    private final String PASSWORD = "base";

    private Connection connection;

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
