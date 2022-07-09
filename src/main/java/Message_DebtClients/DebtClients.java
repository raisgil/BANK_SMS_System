package DebtClients;

import Clients.Client;
import DBworker.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DebtClients {
    public static void main(String[] args) {
        DBWorker dbWorker = new DBWorker();

        String query = "select*from clients where debt > 0";
        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setFirst_nm(resultSet.getString("first_nm"));
                client.setLast_nm(resultSet.getString("last_nm"));
                client.setMiddle_nm(resultSet.getString("middle_nm"));
                client.setDebt(resultSet.getInt("debt"));
                client.setEmail(resultSet.getString("client_mail"));
                String name = resultSet.getString(2);
                System.out.println(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}