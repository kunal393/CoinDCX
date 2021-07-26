package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pages {

	WebDriver driver;
	
	public pages(WebDriver driver) {
	PageFactory.initElements(driver, this);
	this.driver=driver;
	}
	
	
	@FindBy (xpath = "//a[contains(text(),'Login')]")
	public WebElement login_link;
	
	@FindBy(xpath = "//div[@class='header-bottom--right']/button[@routerlink='/signup']")
	public WebElement Register_Btn;
	
	@FindBy (xpath = "//input[@name='firstName']")
	public WebElement signup_firstName;
	
	@FindBy (xpath = "//input[@name='lastName']")
	public WebElement signup_lastName;
	
	@FindBy (xpath = "//input[@name='email']")
	public WebElement signup_email;
		
	@FindBy (xpath = "//input[@name='password']")
	public WebElement signup_password;
		
	@FindBy (xpath = "//input[@name='phone']")
	public WebElement signup_phone;
		
	@FindBy (xpath = "//span[@id='recaptcha-anchor']/div[1]")
	public WebElement signup_recaptcha;
		
	@FindBy (xpath = "//button[@type='submit']")
	public WebElement signup_submit;
	
	@FindBy(xpath = "//*[contains(text(),'m not a robot')]")
	public WebElement captcha_visible;
	
	@FindBy(xpath = "//div/*[contains(text(),'Invalid Email ID')]")
	public WebElement signup_emailAlert;
	
	
	public WebElement getPaaword_Alert(String alert_msg) {
		return driver.findElement(By.xpath("//*[contains(text(),'"+alert_msg+"')]"));
	}
}
