package iPayedAppiumFramework.dummyPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iPayedAppiumFramework.helperClasses.ImageCracker;
import iPayedAppiumFramework.helperClasses.ReadExcelFileData;
import iPayedAppiumFramework.pageObject.EnterPinPage;
import iPayedAppiumFramework.pageObject.EventsPage;
import iPayedAppiumFramework.pageObject.HamburgerMenuPage;
import iPayedAppiumFramework.pageObject.IpayedDigitalIdPage;
import iPayedAppiumFramework.pageObject.LoginPage;
import iPayedAppiumFramework.pageObject.LogoutPopUp;
import iPayedAppiumFramework.pageObject.WelcomePage;
import iPayedAppiumFramework.resources.Base;
import iPayedAppiumFramework.utility.ExcelFileUtilities;
import iPayedAppiumFramework.utility.PageScroll;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class NewTestCase extends Base{
	
	public LoginPage loginPage; 
	public WelcomePage welcomePage; 
	public EventsPage eventsPage;
	public EnterPinPage enterPinPage;
	public IpayedDigitalIdPage ipayedDigitalIdPage;
	public HamburgerMenuPage hamburgerMenuPage;
	public PageScroll pageScroll;
	public ImageCracker imageCracker;
	public LogoutPopUp logoutPopUp;
	@BeforeTest()
	public void killNodes() throws InterruptedException, IOException
	{
		killAllNodes();
	}
	
	@BeforeMethod()
	public void startUp() throws IOException, InterruptedException
	{
		startServer();
		AndroidDriver<AndroidElement> driver = capabilities("iPayedAppName");
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		eventsPage = new EventsPage(driver);
		welcomePage = new WelcomePage(driver);
		loginPage = new LoginPage(driver);
		enterPinPage = new EnterPinPage(driver);
		ipayedDigitalIdPage = new IpayedDigitalIdPage(driver);
		hamburgerMenuPage = new HamburgerMenuPage (driver);
		pageScroll = new PageScroll(driver);
		imageCracker = new ImageCracker();
		logoutPopUp = new LogoutPopUp(driver);
		welcomePage.skipButton.click();
		eventsPage.iPayedLogoOnEventPage.click();
		loginPage.deviceLocationAllowButtonOnPopup.click();
		
		Thread.sleep(2000);
		
		loginPage.deviceLocationAllowButtonOnPopup.click();
	}
	
	
	
//	@Test
//	public void verifyErrorMessageWithoutEnterCredentials() throws IOException, InterruptedException
//	{
//		loginPage.loginButton.click();
//		Base.getScreenShot("errorScreenShot");
//		Thread.sleep(1000);
//		String error = imageCracker.getImgText("D:\\Selenium\\iPayedAppiumFramework\\screenShots\\errorScreenShot.png");
//		System.out.println(error);
//	}
	
	
	@AfterMethod()
	public void closeUp() throws IOException, InterruptedException
	{
		driver.closeApp();
		killEmulator();
		service.stop();
	}
}
