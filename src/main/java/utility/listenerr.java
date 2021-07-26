package utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.IReport;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class listenerr extends BaseClass implements ITestListener{




	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getName());
		report.flush();
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		report.flush();
	}

	public void onTestFailure(ITestResult result) {


		try {
			screen(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test.log(Status.FAIL, result.getThrowable().fillInStackTrace(), MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+"/Screens/"+screenshot).build());
		} catch (IOException e) {
			test.log(Status.FAIL,e.getMessage());
		}
		report.flush();
		
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}
}
