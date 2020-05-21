package iPayedAppiumFramework.pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EventsPage {
	
	public EventsPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.app.iPayed:id/logoIpayed")
	public WebElement iPayedLogoOnEventPage;
	
	@AndroidFindBy(id="com.app.iPayed:id/eventNameID")
	public List<WebElement> eventName;
	
	@AndroidFindBy(id="com.app.iPayed:id/eventItemCardView")
	public List<WebElement> eventCardView;
	
	@AndroidFindBy(id="com.app.iPayed:id/recycler_view")
	public WebElement recyclerView;
	
	@AndroidFindBy(id="com.app.iPayed:id/bookmarkIcon")
	public WebElement bookMarkIcon;
	
	@AndroidFindBy(id="com.app.iPayed:id/EventAddressID")
	public WebElement eventAddress;
	
	@AndroidFindBy(id="com.app.iPayed:id/icon")
	public WebElement hamburgerIcon;

}
