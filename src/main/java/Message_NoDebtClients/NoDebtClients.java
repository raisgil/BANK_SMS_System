package Message_NoDebtClients;

import DBworker.DBWorker;
import FileMaker.CreateExlFile;

import java.sql.SQLException;
import java.sql.Statement;

public class NoDebtClients extends CreateExlFile {
    public static void main(String[] args) {
        DBWorker dbWorkerNoDebt = new DBWorker();
        CreateExlFile createExlFile = new CreateExlFile();
        String query = "select*from clients where debt = 0";
        try {
            Statement statement = dbWorkerNoDebt.getConnection().createStatement();
            createExlFile.createFileDebt(statement.executeQuery(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
