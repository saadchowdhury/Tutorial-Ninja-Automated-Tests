package org.example;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadFile {

    public Map[] readExcel(String fileName, String sheetName) throws IOException {


//        File myFile = new File(filePath + "\\" + fileName);
//        FileInputStream inputStream = new FileInputStream(myFile);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        //System.out.println(fileExtensionName);

        Workbook myWorkbook = null;

        if(fileExtensionName.equals(".xlsx")){

            myWorkbook = new XSSFWorkbook(inputStream);

        }

        else if (fileExtensionName.equals(".xls")) {

            myWorkbook = new HSSFWorkbook(inputStream);

        } else {
            System.out.println("Wrong File Type");
        }

        //Read sheet inside the workbook by its name

        Sheet mySheet = myWorkbook.getSheet(sheetName);

        int rowCount = mySheet.getLastRowNum() - mySheet.getFirstRowNum();
        int colCount = mySheet.getRow(0).getPhysicalNumberOfCells();
        DataFormatter myData = new DataFormatter();
        String excelData[][] = new String[rowCount+1][colCount+1];
        for (int i = 0; i < rowCount+1; i++) {

            Row row = mySheet.getRow(i);

            for (int j = 0; j < row.getLastCellNum(); j++) {

                if(row.getCell(j)!=null) {
                    excelData[i][j] = myData.formatCellValue(row.getCell(j));
                }else{
                    excelData[i][j] = "";
                }

            }


        }
        //this part takes the string data to a map and sends to the main file
        Map<String,String> map[] = new Map[excelData.length];
        for (int i=1; i<rowCount+1 ; i++)
        {
            map[i] = new HashMap<String,String>();
            for(int j=0; j<colCount; j++)
            {
                String key = excelData[0][j];
                String value = excelData[i][j];

                map[i].put(key, value);

            }
            System.out.println(map[i]);
        }

        return  map;

    }
}
