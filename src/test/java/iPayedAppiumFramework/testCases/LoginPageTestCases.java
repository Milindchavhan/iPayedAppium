package iPayedAppiumFramework.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import iPayedAppiumFramework.helperClasses.ReadExcelFileData;
import iPayedAppiumFramework.pageObject.EnterPinPage;
import iPayedAppiumFramework.pageObject.EventsPage;
import iPayedAppiumFramework.pageObject.HamburgerMenuPage;
import iPayedAppiumFramework.pageObject.IpayedDigitalIdPage;
import iPayedAppiumFramework.pageObject.LoginPage;
import iPayedAppiumFramework.pageObject.LogoutPopUp;
import iPayedAppiumFramework.pageObject.WelcomePage;
import iPayedAppiumFramework.resources.Base;
import iPayedAppiumFramework.utility.PageScroll;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPageTestCases extends Base{
	
	public LoginPage loginPage; 
	public WelcomePage welcomePage; 
	public EventsPage eventsPage;
	public EnterPinPage enterPinPage;
	public PageScroll pageScroll;
	public HamburgerMenuPage hamburgerMenuPage;
	public IpayedDigitalIdPage ipayedDigitalIdPage;
	public LogoutPopUp logoutPopUp;
	
	@BeforeTest()
	public void killNodes() throws InterruptedException, IOException
	{
		killAllNodes();
	}
	
	@BeforeMethod()
	public void startUp() throws IOException, InterruptedException
	{
		//killAllNodes();
		startServer();
		AndroidDriver<AndroidElement> driver = capabilities("iPayedAppName");
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		eventsPage = new EventsPage(driver);
		welcomePage = new WelcomePage(driver);
		loginPage = new LoginPage(driver);
		enterPinPage = new EnterPinPage(driver);
		pageScroll = new PageScroll(driver);
		hamburgerMenuPage = new HamburgerMenuPage(driver);
		ipayedDigitalIdPage = new IpayedDigitalIdPage(driver);
		logoutPopUp = new LogoutPopUp(driver);
		
		welcomePage.skipButton.click();
		eventsPage.iPayedLogoOnEventPage.click();
		loginPage.deviceLocationAllowButtonOnPopup.click();
		
		Thread.sleep(2000);
		
		loginPage.deviceLocationAllowButtonOnPopup.click();
	}
	
	@Test
	public void verifyEmailPlaceholder()
	{
		String emailPlaceholder = loginPage.emailTextField.getAttribute("text");
		System.out.println(emailPlaceholder);
		Assert.assertEquals(emailPlaceholder, "Email");
	}
	
	@Test
	public void verifyEmailFieldEnabled()
	{
		boolean emailFieldEnabled = loginPage.emailTextField.isEnabled();
		System.out.println(emailFieldEnabled);
		Assert.assertEquals(emailFieldEnabled, true);
	}
	
	@Test
	public void verifyPasswordPlaceholder()
	{
		String passwordPlaceholder = loginPage.passwordTextField.getAttribute("text");
		Assert.assertEquals(passwordPlaceholder, "Password");
	}
	
	@Test
	public void verifyPasswordFieldEnabled()
	{
		boolean passwordFieldEnabled = loginPage.passwordTextField.isEnabled();
		Assert.assertEquals(passwordFieldEnabled, true);
	}
	
	@Test
	public void verifyShowPasswordFiledEnabled()
	{
		boolean showPasswordFieldEnabled = loginPage.showPasswordField.isEnabled();
		Assert.assertEquals(showPasswordFieldEnabled, true);
	}
	
	@Test(enabled = false)
	public void verifyLoginButtonText()
	{
		String loginButtonText = loginPage.loginButton.getText();
		Assert.assertEquals(loginButtonText, "LOGIN");
	}
	
	@Test
	public void verifyRememberMeUnchecked()
	{
		boolean unchecked = loginPage.rememberME.isSelected();
		Assert.assertEquals(unchecked, false);
	}
	
	@Test 
	public void verifyRemenebrMeIsSelectAndDeselect()
	{
		loginPage.rememberME.click();
		String checked = loginPage.rememberME.getAttribute("checked");
		Assert.assertEquals(checked, "true");
	}
	
	@Test
	public void verifyRememberMeText()
	{
		String rememberMeText = loginPage.rememberME.getAttribute("text");
		Assert.assertEquals(rememberMeText, "Remember me");
	}
	
	@Test
	public void verifyForgotPasswordEnabled()
	{
		boolean forgotPasswordEnabled = loginPage.forgotPassword.isEnabled();
		Assert.assertEquals(forgotPasswordEnabled, true);
	}
	
	@Test
	public void verifyForgotPasswordText()
	{
		String forgotPasswordText = loginPage.forgotPassword.getAttribute("text");
		Assert.assertEquals(forgotPasswordText, "Forgot Password?");
	}
	
	@Test
	public void verifyLoginWithText()
	{
		String loginWithText = loginPage.loginWith.getAttribute("text");
		Assert.assertEquals(loginWithText, "Or login with");
	}
	
	@Test
	public void verifyNotRegisterText()
	{
		String notRegisterText = loginPage.notRegisterYet.getAttribute("text");
		Assert.assertEquals(notRegisterText, "Not Registered Yet?");
	}
	
	@Test
	public void verifySignUpEnabled()
	{
		boolean signUpEnabled = loginPage.signUp.isEnabled();
		Assert.assertEquals(signUpEnabled, true);
	}
	
	@Test
	public void verifySignUpText()
	{
		String signUpText = loginPage.signUp.getAttribute("text");
		Assert.assertEquals(signUpText, "SIGN UP");
	}
	
	@Test
	public void verifyPasswordTextShowPasswordDisabled() throws IOException
	{
		loginPage.passwordTextField.sendKeys(ReadExcelFileData.readSinglePasswordFromExcel());
		String showPassworDisabledPasswordText = loginPage.passwordTextField.getAttribute("text");
		Assert.assertEquals(showPassworDisabledPasswordText, "•••••••••••");
	}
	
	@Test
	public void verifyPasswordTextShowPasswordEnabled() throws IOException
	{
		loginPage.passwordTextField.sendKeys(ReadExcelFileData.readSinglePasswordFromExcel());
		loginPage.showPasswordField.click();
		String showPassworEnabledPasswordText = loginPage.passwordTextField.getAttribute("text");
		Assert.assertEquals(showPassworEnabledPasswordText, "welcome@123");
	}
	
	@Test
	public void withoutEnabledTouchIDCheckToasterMessage()
	{
		loginPage.touchID.click();
		String bioMetricNotEnabledToast = loginPage.touchIDtoasterMessage.getText();
		Assert.assertEquals(bioMetricNotEnabledToast, "Biometrics has not been enabled");
	}
	
	@Test
	public void withoutEnabledPinCheckToasterMessage()
	{
		loginPage.pin.click();
		String toasterMessagePin = loginPage.pinToasterMessage.getText();
		Assert.assertEquals(toasterMessagePin, "PIN has not been enabled");
	}
	
	@Test
	public void verifyPinAndTouchIDText()
	{
		String pinText = loginPage.pinTextElement.getAttribute("text");
		String touchIDText = loginPage.touchIDTextElement.getAttribute("text");
		Assert.assertEquals(pinText, "PIN");
		Assert.assertEquals(touchIDText, "Touch ID");
	}
	
	@Test 
	public void verifyUserLoginWithCorrectCredentials() throws IOException, InterruptedException
	{
		loginPage.emailTextField.clear();
		loginPage.emailTextField.sendKeys(ReadExcelFileData.readSingleUserFromExcel());
		loginPage.passwordTextField.clear();
		loginPage.passwordTextField.sendKeys(ReadExcelFileData.readSinglePasswordFromExcel());
		loginPage.loginButton.click();
		
		Thread.sleep(2000);
		
		enterPinPage.enterPin();
		enterPinPage.confirmPinOKButton.click();
		enterPinPage.enterPin();
		
		enterPinPage.enabledFingurePrintNoButton.click();
		ipayedDigitalIdPage.DigitalIDBackButton.click();
		Thread.sleep(2000);
		eventsPage.hamburgerIcon.click();
		pageScroll.scrollPageToText("Logout");
		boolean logoutElementSize = hamburgerMenuPage.logout.size()>0;
		System.out.println(logoutElementSize);
		Assert.assertEquals(logoutElementSize, true);
	}
	
	@Test 
	public void verifyForgotPasswordLinkWorking() throws IOException, InterruptedException
	{
		loginPage.forgotPassword.click();
		String forgotPassswordPageSource = driver.getPageSource();
		boolean weAreOnForgotPasswordPage = forgotPassswordPageSource.contains("Please enter your email address");
		System.out.println(weAreOnForgotPasswordPage);
		Assert.assertEquals(weAreOnForgotPasswordPage, true);
	}
	
	@Test
	public void verifyRemenberMeWorking() throws IOException, InterruptedException
	{
		loginPage.emailTextField.clear();
		loginPage.emailTextField.sendKeys(ReadExcelFileData.readSingleUserFromExcel());
		loginPage.passwordTextField.clear();
		loginPage.passwordTextField.sendKeys(ReadExcelFileData.readSinglePasswordFromExcel());
		loginPage.rememberME.click();
		loginPage.loginButton.click();
		
		Thread.sleep(2000);
		
		enterPinPage.enterPin();
		enterPinPage.confirmPinOKButton.click();
		enterPinPage.enterPin();
		enterPinPage.enabledFingurePrintNoButton.click();
		ipayedDigitalIdPage.DigitalIDBackButton.click();
		
		Thread.sleep(2000);
		
		eventsPage.hamburgerIcon.click();
		pageScroll.scrollPageToText("Logout");
		hamburgerMenuPage.logout.get(0).click(); 
		logoutPopUp.logoutPopUpYesButton.click();
		String userEmail = loginPage.emailTextField.getAttribute("text");
		Assert.assertEquals(userEmail, ReadExcelFileData.readSingleUserFromExcel());
	}
	
	
	@AfterMethod()
	public void closeUp() throws IOException, InterruptedException
	{
		driver.closeApp();
		killEmulator();
		service.stop();
	}
}
