package iPayedAppiumFramework.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Waits {
	AndroidDriver<AndroidElement> driver;
	WebDriverWait wait = new WebDriverWait(driver,5);
	
	public Waits(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
	}
	
	public void implicitWait()
	{
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	public void explicitWaitForClickable(WebElement element)
	{
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated((By) element));
		//wait.until(ExpectedConditions.invisibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void explicitWaitForVisibilityOfElement(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
