package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {		


	public static WebDriverWait wait;
	public static WebDriverWait waitshort;
	public Properties property;

	public static WebDriver driver;
	public static String screenshot;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports report;
	public static ExtentTest test;


	@BeforeSuite
	public void setup() throws IOException {
		extentReport();
		LaunchBrowser();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver, 4);
	}

	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		//		options.addArguments("--headless");
		//		options.addArguments("--window-size=1280,720");
		options.addArguments("--incognito");
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}


	public void extentReport() {
		reporter = new ExtentHtmlReporter("./TestReports/report.html");
		reporter.config().setTheme(Theme.DARK);
		report=new ExtentReports();
		report.attachReporter(reporter);
		
	}

	public void verify(String actual,String expected) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		}catch (AssertionError e) {
			screen("AsserFail");
			if(test!=null) {
				try {
					test.log(Status.FAIL, "Expected : "+expected+" But Found : "+actual, MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+screenshot+".png").build());
				}catch (Exception g) {
					test.log(Status.FAIL,g.getMessage());
				}
			}
		}
	}


	//to get data from config file 
	public String getProperty(String key) throws IOException {
		property= new Properties();
		FileInputStream file= new FileInputStream("./src/main/java/configuration/config.properties");
		property.load(file);
		return property.getProperty(key);
	}

	public void ClickOnElement(WebElement locator,String component){
		locator.click();
		if(test!=null) {
			test.log(Status.INFO, "Clicking on : " + component);
		}
		System.out.println("Clicking on : " + component);
	}

	public void TypeIn(WebElement locator,String Value,String component){
		locator.sendKeys(Value);
		if(test!=null) {
			test.log(Status.INFO, "Typing in : " + component +", Data : "+Value);
		}
		System.out.println("Typing in : " + component+", Data : "+Value);
	}


	public void screen(String str) throws IOException {
		String Timestamp= new SimpleDateFormat("yyyy.MM.dd - hh.mm.ss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		screenshot=str+"_"+Timestamp+".png";
		System.out.println("screenshot name :"+screenshot);
		FileUtils.copyFile(source, new File("./Screens/"+screenshot));
	}
	public void windowswitch() {
		String parent_window = driver.getWindowHandle();
		Set<String> str = driver.getWindowHandles();
		for (String s : str) {
			if (!s.equals(parent_window)) {
				driver.switchTo().window(s);
			}
		}
	}
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
