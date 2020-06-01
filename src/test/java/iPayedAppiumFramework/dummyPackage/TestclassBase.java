package iPayedAppiumFramework.dummyPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iPayedAppiumFramework.helperClasses.ReadExcelFileData;
import iPayedAppiumFramework.pageObject.EnterPinPage;
import iPayedAppiumFramework.pageObject.EventsPage;
import iPayedAppiumFramework.pageObject.HamburgerMenuPage;
import iPayedAppiumFramework.pageObject.IpayedDigitalIdPage;
import iPayedAppiumFramework.pageObject.LoginPage;
import iPayedAppiumFramework.pageObject.PartyEventPage;
import iPayedAppiumFramework.pageObject.UpcomingEventPage;
import iPayedAppiumFramework.pageObject.WelcomePage;
import iPayedAppiumFramework.resources.Base;
import iPayedAppiumFramework.utility.PageScroll;
import iPayedAppiumFramework.utility.UpcomingEventCount;
import iPayedAppiumFramework.utility.Waits;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestclassBase extends Base{
	
	public WelcomePage welcomePage;
	public EventsPage eventsPage;
	public LoginPage loginPage;
	public EnterPinPage enterPinPage ;
	public PartyEventPage partyEventPage;
	public IpayedDigitalIdPage ipayedDigitalIdPage;
	public PageScroll pageScroll;
	public HamburgerMenuPage hamburgerMenuPage;
	public UpcomingEventPage upcomingEventPage;
	public UpcomingEventCount upcomingEventCount;
	public Waits waits;
	
	
	
	@BeforeTest()
	public void killNodes() throws InterruptedException, IOException
	{
		
		killAllNodes();
	}
	
	@BeforeMethod
	public void userLogin() throws InterruptedException, IOException
	{
		startServer();
		AndroidDriver<AndroidElement> driver = capabilities("iPayedAppName");
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		welcomePage = new WelcomePage(driver);
		eventsPage = new EventsPage(driver);
		loginPage = new LoginPage(driver);
		enterPinPage = new EnterPinPage(driver);
		partyEventPage = new PartyEventPage(driver);
		ipayedDigitalIdPage = new IpayedDigitalIdPage(driver);
		pageScroll = new PageScroll(driver);
		hamburgerMenuPage = new HamburgerMenuPage(driver);
		upcomingEventPage = new UpcomingEventPage(driver);
		upcomingEventCount = new UpcomingEventCount(driver);
		waits = new Waits(driver);
		//waits = new Waits(driver);
		
		welcomePage.skipButton.click();
		eventsPage.iPayedLogoOnEventPage.click();
		loginPage.deviceLocationAllowButtonOnPopup.click();
		Thread.sleep(2000);
		
		loginPage.deviceLocationAllowButtonOnPopup.click();
		loginPage.emailTextField.sendKeys(ReadExcelFileData.readSingleUserFromExcel());
		loginPage.passwordTextField.sendKeys(ReadExcelFileData.readSinglePasswordFromExcel());
		loginPage.loginButton.click();
		
		//waits.explicitWaitForVisibilityOfElement(enterPinPage.pleaseEnterPinText);
		Thread.sleep(2000);
		
		enterPinPage.enterPin();
		enterPinPage.confirmPinOKButton.click();
		enterPinPage.enterPin();
		
		enterPinPage.enabledFingurePrintNoButton.click();
		ipayedDigitalIdPage.DigitalIDBackButton.click();
	}
	@Test(priority =1)
	public void saveYourSpotAtEvent() throws IOException, InterruptedException
	{
		eventsPage.eventName.get(0).click();
		partyEventPage.saveYourSpotButton.click();
		waits.explicitWaitForClickable(partyEventPage.partyEventBackButton);
		//Thread.sleep(2000);
		partyEventPage.partyEventBackButton.click();
		waits.explicitWaitForClickable(eventsPage.hamburgerIcon);
		//Thread.sleep(4000);
		eventsPage.hamburgerIcon.click();
		hamburgerMenuPage.myEvents.click();
		int actualUpcomingCount =upcomingEventCount.getUpcomingEventCount(upcomingEventPage.upcomingEventHeader); 
		Assert.assertEquals(actualUpcomingCount, 1);
	}
	
	@Test(priority=2)
	public void likeEventAndCheckUpcomingEventCount() throws InterruptedException
	{
		eventsPage.eventName.get(1).click();
		partyEventPage.likePartyEventButton.click();
		waits.explicitWaitForClickable(partyEventPage.partyEventBackButton);
		//Thread.sleep(2000);
		partyEventPage.partyEventBackButton.click();
		eventsPage.hamburgerIcon.click();
		hamburgerMenuPage.myEvents.click();
		int actualUpcomingCount =upcomingEventCount.getUpcomingEventCount(upcomingEventPage.upcomingEventHeader); 
		Assert.assertEquals(actualUpcomingCount, 2);
	}
	
	@Test(priority=3)
	public void bookmarkEventCheckUpcomingEventCount() throws InterruptedException
	{
		pageScroll.scrollPageToText("1020 SE 7th Ave,\r\n" + "Portland, OR");
		eventsPage.bookMarkIcon.click();
		//waits.explicitWaitForVisibilityOfElement(eventsPage.hamburgerIcon);
		waits.explicitWaitForClickable(eventsPage.hamburgerIcon);
		//Thread.sleep(2000);
		eventsPage.hamburgerIcon.click();
		hamburgerMenuPage.myEvents.click();
		int actualUpcomingCount =upcomingEventCount.getUpcomingEventCount(upcomingEventPage.upcomingEventHeader); 
		Assert.assertEquals(actualUpcomingCount, 3);
	}
	
	@Test(priority=4)
	public void giveUpYourSpot() throws InterruptedException
	{
		eventsPage.hamburgerIcon.click();
		hamburgerMenuPage.myEvents.click();
		upcomingEventPage.eventMainLayout.get(0).click();
		System.out.println(partyEventPage.saveYourSpotButton.getText());
		partyEventPage.saveYourSpotButton.click();
		waits.explicitWaitForClickable(partyEventPage.partyEventBackButton);
		//Thread.sleep(4000);
		partyEventPage.partyEventBackButton.click();
		int actualUpcomingCount =upcomingEventCount.getUpcomingEventCount(upcomingEventPage.upcomingEventHeader); 
		Assert.assertEquals(actualUpcomingCount, 2);
	}
	
	@Test(priority =5)
	public void dislikeEvent() throws InterruptedException
	{
		eventsPage.hamburgerIcon.click();
		hamburgerMenuPage.myEvents.click();
		upcomingEventPage.eventMainLayout.get(0).click();
		partyEventPage.likePartyEventButton.click();
		waits.explicitWaitForClickable(partyEventPage.partyEventBackButton);
		//Thread.sleep(3000);
		partyEventPage.partyEventBackButton.click();
		int actualUpcomingCount =upcomingEventCount.getUpcomingEventCount(upcomingEventPage.upcomingEventHeader); 
		Assert.assertEquals(actualUpcomingCount, 1);
	}
	
	@Test(priority = 6)
	public void unbookMarkEvent() throws InterruptedException
	{
		eventsPage.hamburgerIcon.click();
		hamburgerMenuPage.myEvents.click();
		upcomingEventPage.eventMainLayout.get(0).click();
		waits.explicitWaitForClickable(partyEventPage.partyEventBackButton);
		//Thread.sleep(3000);
		partyEventPage.bookmarkPartyEventButton.click();
		partyEventPage.partyEventBackButton.click();
		Assert.assertTrue(upcomingEventPage.noEventsAvailable.isDisplayed());	
	}
	
	@AfterMethod
	public void closeUp() throws IOException, InterruptedException
	{
		driver.closeApp();
		killEmulator();
		service.stop();
	}

}
