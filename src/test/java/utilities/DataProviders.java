package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        String path = "C:\\Users\\Aniket\\eclipse-workspace\\opencart\\testData\\OpenCart_LoginData.xlsx";

        ExcelUtility xlutil = new ExcelUtility(path);

        int totalRows = xlutil.getRowCount("Sheet1");   // includes header
        int totalCols = xlutil.getCellCount("Sheet1", 0);

        // exclude header row
        String[][] loginData = new String[totalRows - 1][totalCols];

        for (int i = 1; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }

        return loginData;
    }
}
