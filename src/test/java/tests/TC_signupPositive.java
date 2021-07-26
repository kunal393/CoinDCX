package tests;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pages.pages;
import utility.BaseClass;
import utility.Datadriven;

public class TC_signupPositive extends BaseClass{

	

	
	pages pagesObj;
		
	@BeforeTest
	public void precondition() throws IOException {
		pagesObj=new pages(driver);
		driver.get(getProperty("url"));
		wait.until(ExpectedConditions.visibilityOf(pagesObj.login_link));
		ClickOnElement(pagesObj.login_link, "Login link");
		windowswitch();
		ClickOnElement(pagesObj.Register_Btn, "Register button");
		wait.until(ExpectedConditions.visibilityOf(pagesObj.signup_firstName));
	}
		
	@BeforeMethod
	public void refresh() {
		driver.navigate().refresh();
	}
		
	
	@Test
	public void email_Negative_Scenario() {
		TypeIn(pagesObj.signup_firstName, "test", "Firstname");
		TypeIn(pagesObj.signup_lastName, "test", "Lastname");
		TypeIn(pagesObj.signup_email, "wkjfksfs", "Invalid email");
		TypeIn(pagesObj.signup_password, "Pas#@$12sword", "Password");
		Assert.assertEquals(pagesObj.signup_emailAlert.getText(), "Invalid Email ID");
		test.pass("After adding invalid email, Email Alert showing properly.");
	}
	
	@Test
	public void test_Signup_positive_flow() throws InterruptedException, AWTException {
		TypeIn(pagesObj.signup_firstName,"test", "Firstname");
		TypeIn(pagesObj.signup_lastName,"test", "Lastname");
		TypeIn(pagesObj.signup_email,"test@testgmail.com", "email");
		TypeIn(pagesObj.signup_password,"Pas#@$12sword", "Password");
		TypeIn(pagesObj.signup_phone,"8970202020", "Phone");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", pagesObj.signup_submit);
		Assert.assertFalse(pagesObj.signup_submit.isEnabled());
		test.pass("Register button is Not enabled.");
	}
	
	
}
