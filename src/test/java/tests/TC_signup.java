package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

import bsh.org.objectweb.asm.Type;
import junit.framework.Assert;
import pages.pages;
import utility.Datadriven;
import utility.BaseClass;

public class TC_signup extends BaseClass implements ITest{
	Datadriven dataObj;
	pages pagesObj;
	private ThreadLocal<String> testName = new ThreadLocal<String>();
	
	@BeforeTest
	public void precondition() throws IOException {
		pagesObj=new pages(driver);
		dataObj=new Datadriven();
		driver.get(getProperty("url"));
		wait.until(ExpectedConditions.visibilityOf(pagesObj.login_link));
		ClickOnElement(pagesObj.login_link, "Login link");
		windowswitch();
		ClickOnElement(pagesObj.Register_Btn, "Register button");
		wait.until(ExpectedConditions.visibilityOf(pagesObj.signup_firstName));
	}
		
	@BeforeMethod
	public void refresh(Method method,Object[] testData) {
		driver.navigate().refresh();
		testName.set(method.getName()+" with data "+testData[0]);
	}
	
	
	

	@Test(dataProvider = "Data")
	public void phone_Negative_Scenario(String password,String Alert) throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(pagesObj.signup_firstName));
		TypeIn(pagesObj.signup_password, password, "Password");
		TypeIn(pagesObj.signup_phone, "8970202020", "Phone");
		Assert.assertEquals(pagesObj.getPaaword_Alert(Alert).getText(), Alert);
		test.pass("Alert message shown properly for password "+password);
	}
	

	@DataProvider
	public Object[][] Data() throws IOException {
		return dataObj.datas("password") ;
	}
	

	@Override
	public String getTestName() {
		return testName.get();
	}
}
