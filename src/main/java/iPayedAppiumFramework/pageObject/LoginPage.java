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
	
	@AndroidFindBy(id="com.app.iPayed:id/paswordEnableLogin")
	public WebElement showPasswordField;
	
	@AndroidFindBy(id="com.app.iPayed:id/rememberMe")
	public WebElement rememberME;
	
	@AndroidFindBy(id="com.app.iPayed:id/forgetPassword")
	public WebElement forgotPassword;
	
	@AndroidFindBy(xpath="//*[@class='android.widget.TextView' and @bounds='[406,1158][674,1223]']")
	public WebElement loginWith;
	
	@AndroidFindBy(id="com.app.iPayed:id/notRegister")
	public WebElement notRegisterYet;
	
	@AndroidFindBy(id="com.app.iPayed:id/tv_to_signup")
	public WebElement signUp;
	
	@AndroidFindBy(id="com.app.iPayed:id/touchID")
	public WebElement touchID;
	
	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	public WebElement touchIDtoasterMessage;
	
	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	public WebElement pinToasterMessage;
	
	@AndroidFindBy(id="com.app.iPayed:id/pincode")
	public WebElement pin;
	
	@AndroidFindBy(xpath="//*[@class='android.widget.TextView' and @bounds='[300,1529][462,1586]']")
	public WebElement touchIDTextElement;
	
	@AndroidFindBy(xpath="//*[@class='android.widget.TextView' and @bounds='[666,1529][734,1586]']")
	public WebElement pinTextElement;
	////*[@id = 'com.app.iPayed:id/rel_touch']/following-sibling::android.widget.RelativeLayout
	
}
