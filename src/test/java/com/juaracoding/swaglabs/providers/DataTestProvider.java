package com.juaracoding.swaglabs.providers;

import com.juaracoding.swaglabs.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class DataTestProvider {

    @DataProvider(name = "loginDataProvider")
    public static Object[][] loginDataProvider() {
        String excelPath = "src/test/resources/DataTestProvider.xlsx"; // sesuaikan path Excel kamu
        String sheetName = "Sheet1";  // sesuaikan sheet name

        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();

        List<Object[]> data = new ArrayList<>();

        // Mulai dari baris 1, karena baris 0 adalah header
        for (int i = 1; i < rowCount; i++) {
            String username = excel.getCellData(i, 0);
            String password = excel.getCellData(i, 1);
            String expected = excel.getCellData(i, 2);
            data.add(new Object[]{username, password, expected});
        }

        return data.toArray(new Object[0][]);
    }
}
