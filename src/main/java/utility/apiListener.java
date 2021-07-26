package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class apiListener implements ITestListener{
	public ExtentHtmlReporter apireporter;
	public ExtentReports apireport;
	public ExtentTest apitest;

	public void extentReport() {
		apireporter = new ExtentHtmlReporter("./TestReports/reportApi.html");
		apireporter.config().setTheme(Theme.DARK);
		apireport=new ExtentReports();
		apireport.attachReporter(apireporter);
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		apitest=apireport.createTest(result.getName());
		apireport.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		apireport.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		apitest.log(Status.FAIL, result.getThrowable().getMessage());
		apireport.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		extentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

}
