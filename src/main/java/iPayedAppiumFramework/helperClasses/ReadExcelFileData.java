package iPayedAppiumFramework.helperClasses;

import java.io.IOException;

import iPayedAppiumFramework.utility.ExcelFileUtilities;

public class ReadExcelFileData {
	
	public static ExcelFileUtilities excelFileUtilities = new ExcelFileUtilities();
	
	public static String[][] readUserNameAndPasswordFromExcel() throws IOException
	{
		String excelFilePath = System.getProperty("user.dir"+"\\src\\main\\java\\iPayedAppiumFramework\\resources\\Data.xlsx");
		int rowNum = excelFileUtilities.getRowCount(excelFilePath, "Sheet1");
		int cellNum = excelFileUtilities.getCellCount(excelFilePath, "Sheet1", 1);
		String userNamePassword[][] = new String [rowNum][cellNum];
		for(int i =1; i<=rowNum; i++)
		{
			for(int j =0; j<=cellNum-1; j++)
			{
			userNamePassword[i-1][j] = excelFileUtilities.getCellData(excelFilePath, "sheet1", i, j);
			}
		}
		return userNamePassword;
	}
	
	public static String readSingleUserFromExcel() throws IOException
	{
		String excelFilePath = System.getProperty("user.dir")+"\\src\\main\\java\\iPayedAppiumFramework\\resources\\Data.xlsx";
		String userNameFromExcel = excelFileUtilities.getCellData(excelFilePath, "Sheet1",1,0);
		return userNameFromExcel;
	}
	
	public static String readSinglePasswordFromExcel() throws IOException
	{
		String passwordFromExcel = excelFileUtilities.getCellData(System.getProperty("user.dir")+"\\src\\main\\java\\iPayedAppiumFramework\\resources\\Data.xlsx", "Sheet1",1,1);
		return passwordFromExcel;
	}
}
