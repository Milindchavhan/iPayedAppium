package iPayedAppiumFramework.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Waits {
	
	AndroidDriver<AndroidElement> driver;
	WebDriverWait wait;
	
	public Waits(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,5);
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
	
//	public void fluentWait(WebElement element)
//	{
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//			       .withTimeout(30, TimeUnit.SECONDS)
//			       .pollingEvery(5, TimeUnit.SECONDS)
//			       .ignoring(NoSuchElementException.class);
//
//			   element = wait.until(new Function<WebDriver, WebElement>() 
//			   {
//			     public WebElement apply(WebDriver driver) {
//			       return driver.findElement(By.id((ToString) element));
//			     }
//			   });
//	}

}
