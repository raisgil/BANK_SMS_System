package Message_OverpayedClients;

import DBworker.DBWorker;
import FileMaker.CreateExlFile;

import java.sql.SQLException;
import java.sql.Statement;

public class OverpayedClients {
    public static void main(String[] args) {
        DBWorker dbWorkerOverpayed = new DBWorker();
        CreateExlFile createExlFile = new CreateExlFile();
        String query = "select*from clients where debt < 0";
        try {
            Statement statement = dbWorkerOverpayed.getConnection().createStatement();
            createExlFile.createFileDebt(statement.executeQuery(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
