package iPayedAppiumFramework.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PartyEventPage {
	public PartyEventPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.app.iPayed:id/attendEventButton")
	public WebElement saveYourSpotButton;
	
	@AndroidFindBy(id="com.app.iPayed:id/details")
	public WebElement saveYourSpotToasterMessage;
	
	@AndroidFindBy(id="com.app.iPayed:id/eventDtlback")
	public WebElement partyEventBackButton;
	
	@AndroidFindBy(id="com.app.iPayed:id/ib_fav")
	public WebElement likePartyEventButton;
	
	@AndroidFindBy(id="com.app.iPayed:id/ib_interested")
	public WebElement bookmarkPartyEventButton;
}
