package iPayedAppiumFramework.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import iPayedAppiumFramework.utility.ExcelFileUtilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	
	public static AppiumDriverLocalService service;	
	public static AndroidDriver<AndroidElement> driver;
	
	public AppiumDriverLocalService startServer()
	{
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		return service;
	}
	
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("cmd /c start "+System.getProperty("user.dir")+"\\src\\main\\java\\iPayedAppiumFramework\\resources\\startEmulator-Shortcut.lnk");
		Thread.sleep(10000);
	}
	
	public static void killAllNodes() throws InterruptedException, IOException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	 
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\iPayedAppiumFramework\\resources\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
	
		File appDirectory = new File("src");
		File app = new File(appDirectory,(String) prop.get("iPayedAppName"));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		String deviceName = (String) prop.get("device");
		startEmulator();
		
		Thread.sleep(10000);
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		return driver;
	}
	
	public static void getScreenShot(String s) throws IOException
	{
		File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File (System.getProperty("user.dir"+"\\screenShots\\"+s+".png")));
	}
	
	public static String[][] readUserNameAndPasswordFromExcel() throws IOException
	{
		String excelFilePath = "D:\\Selenium\\iPayedAppiumFramework\\src\\main\\java\\iPayedAppiumFramework\\resources\\Data.xlsx";
		ExcelFileUtilities excelFileUtilities = new ExcelFileUtilities();
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
}
