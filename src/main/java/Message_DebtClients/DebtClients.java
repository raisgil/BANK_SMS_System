package Message_DebtClients;

import DBworker.DBWorker;
import FileMaker.CreateExlFile;

import java.sql.SQLException;
import java.sql.Statement;

public class DebtClients extends CreateExlFile {
    public static void main(String[] args) {
        DBWorker dbWorkerDebt = new DBWorker();
        CreateExlFile createExlFile = new CreateExlFile();
        String query = "select*from clients where debt > 0";
        try {
            Statement statement = dbWorkerDebt.getConnection().createStatement();
            createExlFile.createFileDebt(statement.executeQuery(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}