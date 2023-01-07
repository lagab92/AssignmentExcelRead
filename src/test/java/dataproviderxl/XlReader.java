package dataproviderxl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XlReader {

    public String[][] readXl(String xlPath, String sheetName) throws IOException, IOException {

        String path = xlPath;

        FileInputStream fis = new FileInputStream(path);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet ws = wb.getSheet(sheetName);

        int rows = ws.getLastRowNum()+1;
        int cols = ws.getRow(0).getLastCellNum();

        String[][] data = new String[rows][cols];

        for (int r = 1; r < rows; r++){

            XSSFRow myRow = ws.getRow(r);

            for (int c = 0; c<cols; c++){

                XSSFCell cell = myRow.getCell(c);
                String value = getCellData(cell);
                data[r][c] = value;

            }

        }

        return data;
    }

    public static String getCellData(Cell cellValue){

        String value;

        DataFormatter formatter = new DataFormatter();

        try {

            value = formatter.formatCellValue(cellValue);

        }catch (Exception e){

            value = "";

        }

        return value;

    }

    public static String getCellValue(Cell cellValue){
        Object value = null;

        switch (cellValue.getCellType())
        {
            case STRING:
                value = cellValue.getStringCellValue();
                break;
            case NUMERIC:
                value = cellValue.getNumericCellValue();
                break;
            case BOOLEAN:
                value = cellValue.getBooleanCellValue();
                break;
        }
        return value.toString();
    }
}
