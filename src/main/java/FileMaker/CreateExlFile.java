package FileMaker;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.ResultSet;

public class test_CreateExlFile {
    public void test_CreateExlFile(ResultSet resultSet, String text1, String text2) {
        try {
            String filename = "C:\\raisgil_project\\СМС_Должники\\SMS_debt_clients.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("SMS_debt_clients");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("database_ID");
            rowhead.createCell(1).setCellValue("ФИО");
            rowhead.createCell(2).setCellValue("Номер_телефона");
            rowhead.createCell(3).setCellValue("Текст_СМС");
            while (resultSet.next()) {
                HSSFRow row = sheet.createRow(resultSet.getRow());
                String nameMiddlename = resultSet.getString("first_nm") + " "
                        + resultSet.getString("middle_nm");
                row.createCell(0).setCellValue(resultSet.getInt("id"));
                row.createCell(1).setCellValue(resultSet.getString("last_nm") + " " + nameMiddlename);
                row.createCell(2).setCellValue(resultSet.getString("phone"));
                row.createCell(3).setCellValue("Уважаемый " + nameMiddlename
                        + text1 + resultSet.getInt("debt") + text2);
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Реестр на отправку СМС создан!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }



        public void test_CreateExlFileno(ResultSet resultSet, String text1) {
            try {
                String filename = "C:\\raisgil_project\\СМС_Клиенты_без_долга\\SMS_debt_clients.xls";
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("SMS_debt_clients");

                HSSFRow rowhead = sheet.createRow((short) 0);
                rowhead.createCell(0).setCellValue("database_ID");
                rowhead.createCell(1).setCellValue("ФИО");
                rowhead.createCell(2).setCellValue("Номер_телефона");
                rowhead.createCell(3).setCellValue("Текст_СМС");
                while (resultSet.next()) {
                    HSSFRow row = sheet.createRow(resultSet.getRow());
                    String nameMiddlename = resultSet.getString("first_nm") + " "
                            + resultSet.getString("middle_nm");
                    row.createCell(0).setCellValue(resultSet.getInt("id"));
                    row.createCell(1).setCellValue(resultSet.getString("last_nm") + " " + nameMiddlename);
                    row.createCell(2).setCellValue(resultSet.getString("phone"));
                    row.createCell(3).setCellValue("Уважаемый " + nameMiddlename
                            + text1);
                }

                FileOutputStream fileOut = new FileOutputStream(filename);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                System.out.println("Реестр на отправку СМС создан!");

            } catch (Exception e) {
                System.out.println(e);
            }
    }
}
