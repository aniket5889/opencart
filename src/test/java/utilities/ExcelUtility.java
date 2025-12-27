package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {

    String path;
    FileInputStream fi;
    FileOutputStream fo;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;
    CellStyle style;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();

        workbook.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        int cellCount = (row == null) ? 0 : row.getLastCellNum();

        workbook.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rownum, int column) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        if (sheet == null) return "";

        row = sheet.getRow(rownum);
        if (row == null) return "";

        cell = row.getCell(column);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        String data = formatter.formatCellValue(cell);

        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {

        File xlfile = new File(path);
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close();
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if (workbook.getSheetIndex(sheetName) == -1)
            workbook.createSheet(sheetName);

        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null)
            sheet.createRow(rownum);

        row = sheet.getRow(rownum);
        cell = row.createCell(column);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    public void fillGreenColour(String sheetName, int rownum, int column) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    public void fillRedColour(String sheetName, int rownum, int column) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }
}
