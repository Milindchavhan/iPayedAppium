package iPayedAppiumFramework.utility;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class UpcomingEventCount {
	AndroidDriver<AndroidElement> driver;
	
	public UpcomingEventCount(AndroidDriver driver)
	{
		this.driver = driver;
	}
	
	public int getUpcomingEventCount(WebElement upcomingEventHeader)
	{
		String upcomingEventHeaderText = upcomingEventHeader.getAttribute("text");
		String numberFromUpcomingEventHeader = upcomingEventHeaderText.replaceAll("[^0-9]", "");
		int upcomingEventCount = Integer.parseInt(numberFromUpcomingEventHeader);
		System.err.println(upcomingEventCount);
		return upcomingEventCount;
	}
	
	

}
