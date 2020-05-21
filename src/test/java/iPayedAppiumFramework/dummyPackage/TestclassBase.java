package iPayedAppiumFramework.dummyPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
		welcomePage.skipButton.click();
		eventsPage.iPayedLogoOnEventPage.click();
		loginPage.deviceLocationAllowButtonOnPopup.click();
		
		Thread.sleep(2000);
		
		loginPage.deviceLocationAllowButtonOnPopup.click();
		loginPage.emailTextField.sendKeys("testpr1@yopmail.com");
		loginPage.passwordTextField.sendKeys("welcome@123");
		loginPage.loginButton.click();
		
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
		Thread.sleep(2000);
		partyEventPage.partyEventBackButton.click();
		Thread.sleep(4000);
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
		Thread.sleep(2000);
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
		Thread.sleep(2000);
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
		Thread.sleep(4000);
		partyEventPage.partyEventBackButton.click();
		Thread.sleep(3000);
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
		Thread.sleep(3000);
		partyEventPage.partyEventBackButton.click();
		Thread.sleep(3000);
		int actualUpcomingCount =upcomingEventCount.getUpcomingEventCount(upcomingEventPage.upcomingEventHeader); 
		Assert.assertEquals(actualUpcomingCount, 1);
	}
	
	@Test(priority = 6)
	public void unbookMarkEvent() throws InterruptedException
	{
		eventsPage.hamburgerIcon.click();
		hamburgerMenuPage.myEvents.click();
		upcomingEventPage.eventMainLayout.get(0).click();
		Thread.sleep(3000);
		partyEventPage.bookmarkPartyEventButton.click();
		partyEventPage.partyEventBackButton.click();
		Assert.assertTrue(upcomingEventPage.noEventsAvailable.isDisplayed());
		
	}
	
	@AfterMethod
	public void closeUp()
	{
		service.stop();
	}

}
