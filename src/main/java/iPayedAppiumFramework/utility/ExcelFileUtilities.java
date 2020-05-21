package iPayedAppiumFramework.utility;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtilities {
	
	public static FileInputStream fi;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public int getRowCount(String xlFile, String xlSheet) throws IOException
	{
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException
	{
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		//ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData(String xlFile, String xlSheet, int rowNum, int cellNum) throws IOException
	{
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(cellNum);
		String data;
		try
		{
			DataFormatter dataFormatter = new DataFormatter();
			String cellData = dataFormatter.formatCellValue(cell);
			return cellData;
		}
		catch(Exception e)
		{
			data = "";
		}
		wb.close();
		fi.close();
		return data;
		
	}

}
