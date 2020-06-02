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
	
	public static void killEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
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
		
		Thread.sleep(15000);
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability("appActivity", ".ContactManager");
		capabilities.setCapability("appPackage", "com.example.android.contactmanager");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		//capabilities.setCapability(deviceReadyTimeout,“10”);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);
		capabilities.setCapability("–session-override",true);
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		return driver;
	}
	
	public static void getScreenShot(String s) throws IOException
	{
		File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File (System.getProperty("user.dir")+"\\screenShots\\"+s+".png"));
	}
}
