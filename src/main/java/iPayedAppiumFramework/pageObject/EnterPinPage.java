package iPayedAppiumFramework.pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EnterPinPage {
	public EnterPinPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	 
	@AndroidFindBy(className="android.widget.EditText")
	public List<WebElement> pinRadioButtons;
	
	@AndroidFindBy(id="android:id/button1")
	public WebElement confirmPinOKButton;
	
	@AndroidFindBy(id="com.app.iPayed:id/cancelFingerprintButton")
	public WebElement enabledFingurePrintNoButton;
	
	@AndroidFindBy(id="com.app.iPayed:id/tv_pin")
	public WebElement pleaseEnterPinText;
	
	public void enterPin()
	{
		for(int i = 0; i<=3;i++)
		{
		pinRadioButtons.get(i).sendKeys("1");
		}
	}

}
