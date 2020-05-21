package iPayedAppiumFramework.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
	
	public LoginPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
	public WebElement deviceLocationAllowButtonOnPopup;
	
	@AndroidFindBy(id="com.app.iPayed:id/input_email")
	public WebElement emailTextField;
	
	@AndroidFindBy(id="com.app.iPayed:id/input_password")
	public WebElement passwordTextField;
	
	@AndroidFindBy(id="com.app.iPayed:id/btn_login")
	public WebElement loginButton;

}
