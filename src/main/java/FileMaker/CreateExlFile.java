package FileMaker;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;

public class CreateExlFile {
    public void createFileDebt(ResultSet resultSet) {
        try {
            String filename_debt = "C:\\raisgil_project\\СМС_Должники\\SMS_debt_clients" + System.currentTimeMillis() + ".xls";
            String filename_no_debt = "C:\\raisgil_project\\СМС_Клиенты_без_долга\\SMS_no_debt_clients" + System.currentTimeMillis() + ".xls";
            String filename_overpayed = "C:\\raisgil_project\\СМС_Переплата\\SMS_overpayed_clients" + System.currentTimeMillis() + ".xls";
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
                if (resultSet.getInt("debt") > 0) {
                    row.createCell(3).setCellValue("Уважаемый " + nameMiddlename
                            + ", Ваша задолженность перед Банком составляет: " + resultSet.getInt("debt")
                            + " руб. Просьба оплатить задолженность.");
                    saveFile(workbook, filename_debt);
                } else if (resultSet.getInt("debt") == 0) {
                    row.createCell(3).setCellValue("Уважаемый " + nameMiddlename
                            + ", у Вас нет просроченной задолженности перед Банком ");
                    saveFile(workbook, filename_no_debt);
                } else if (resultSet.getInt("debt") < 0) {
                    row.createCell(3).setCellValue("Уважаемый " + nameMiddlename
                            + ", переплата по кредитным обязательствам составляет: " + resultSet.getInt("debt") * (-1)
                            + " руб. Средства будут зачислены в счет следующего платежа.");
                    saveFile(workbook, filename_overpayed);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Реестр на отправку СМС создан!");
    }

    public static void saveFile(HSSFWorkbook workbook, String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }


}
