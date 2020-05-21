package iPayedAppiumFramework.utility;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidActions {
	
	AndroidDriver driver;
	public AndroidActions(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
	}
	
	TouchAction t = new TouchAction(driver);
	
	public void dragAndDrop(WebElement source, WebElement destination)
	{
		t.longPress(element(source)).moveTo(element(destination)).release().perform();
	}
	
	public void tapOnElement(WebElement element)
	{
		t.tap(tapOptions().withElement(element(element))).perform();
	}
	
	public void longPressOnElement(WebElement element)
	{
		t.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(2))).release().perform();
	}
	

}
