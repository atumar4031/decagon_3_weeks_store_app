package org.atumar4031.inputOutputs;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.atumar4031.constants.Category;
import org.atumar4031.exceptions.EmptyInputException;
import org.atumar4031.model.Product;

import java.io.FileInputStream;
import java.io.IOException;

public class Reading {
    public static Product[] readProductFromExcel() throws IOException, EmptyInputException {
        String path = "src/main/java/org/atumar4031/constants/phoneStore.xlsx";

        FileInputStream inputStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        Product[] products = new Product[rowCount];
        for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++){
            XSSFRow row = sheet.getRow(rowIndex);

            Category category = new Category(
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    row.getCell(6).getStringCellValue()
            );

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
