package org.atumar4031.inputOutputs;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.atumar4031.enums.Category;
import org.atumar4031.exceptions.InvalidInputException;
import org.atumar4031.model.Product;

import java.io.FileInputStream;
import java.io.IOException;

public class Reading {

    public static Product[] readProductFromExcel() throws IOException, InvalidInputException {
        String path = "src/main/java/org/atumar4031/files/phoneStore.xlsx";

        FileInputStream inputStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        Product[] products = new Product[rowCount];
        for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++){
            XSSFRow row = sheet.getRow(rowIndex);

            Category category = null;
            switch (row.getCell(2).getStringCellValue()){
                case "phone" -> category = Category.PHONE;
                case "headset" -> category = Category.HEADSET;
                case "charger" -> category = Category.CHARGER;
                case "other" -> category = Category.OTHER;
            }
            products[rowIndex - 1] = new Product(
                    (int) row.getCell(0).getNumericCellValue(),
                          row.getCell(1).getStringCellValue(),
                    (double) row.getCell(4).getNumericCellValue(),
                    (int) row.getCell(5).getNumericCellValue(),
                    category,
                    row.getCell(5).getNumericCellValue() > 0? "Available" : "Product out of stock"
            );      // Product(int productId, String name, double price,int quantity, Category category)
            }
        return products;
    }
}
