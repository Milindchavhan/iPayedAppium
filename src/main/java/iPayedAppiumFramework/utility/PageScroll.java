package iPayedAppiumFramework.utility;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PageScroll {
	
	AndroidDriver<AndroidElement> driver;
	public PageScroll(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
	}
	
	public void scrollPageToText(String text)
	{
		driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	}
	
	public void scrollPageToTextWithOtherResourceID(String resourceID, String text)
	{
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+resourceID+"\")).scrollIntoView(new UiSelector().textMatches(\""+text+"\").instance(0))"));
	}
}
