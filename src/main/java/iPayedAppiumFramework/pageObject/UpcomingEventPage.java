package iPayedAppiumFramework.pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class UpcomingEventPage {
	public UpcomingEventPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Upcoming\")")
	public WebElement upcomingEventHeader;
	
	@AndroidFindBy(id="com.app.iPayed:id/mainLayout")
	public List<WebElement> eventMainLayout;
	
	@AndroidFindBy(id="com.app.iPayed:id/noAttendedEvent")
	public WebElement noEventsAvailable;
	

}
