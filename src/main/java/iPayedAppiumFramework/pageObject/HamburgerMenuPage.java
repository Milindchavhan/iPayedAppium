package iPayedAppiumFramework.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HamburgerMenuPage {
	
	public HamburgerMenuPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(xpath="//*[@text='My Events']")
	public WebElement myEvents;

}
