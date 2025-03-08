package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener  {
	public ExtentSparkReporter sparkrepoter;
	public ExtentReports report;
	public ExtentTest test;

	String reportname;

	public void onStart(ITestContext testcontext) {

		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		reportname="Test-Report-"+timestamp+".html";

		sparkrepoter=new ExtentSparkReporter("./reports/"+reportname);
		sparkrepoter.config().setDocumentTitle("Opencart Automation Report");
		sparkrepoter.config().setReportName("opencart testing");
		sparkrepoter.config().setTheme(Theme.DARK);

		report=new ExtentReports();
		report.attachReporter(sparkrepoter);
		report.setSystemInfo("Application", "Opencart");
		report.setSystemInfo("Module", "Admin");
		report.setSystemInfo("Submodule", "customer");
		report.setSystemInfo("username", System.getProperty("user.name"));
		report.setSystemInfo("Enviroment", "QA");

		String os=testcontext.getCurrentXmlTest().getParameter("os");
		report.setSystemInfo("Operating System", os);

		String browser=testcontext.getCurrentXmlTest().getParameter("browser");
		report.setSystemInfo("Browser Nmae", browser);

		List<String> includedGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			report.setSystemInfo("Group Nmaes", includedGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test=report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"Testcase Passed");
	}

	public void onTestFailure(ITestResult result) {
		test=report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+"Testcase Failed");
		test.log(Status.INFO,result.getThrowable().getLocalizedMessage());

		try {
			String imagepath=new BaseClass().captureScreenShot(result.getName());
			test.addScreenCaptureFromPath(imagepath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test=report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"Testcase Skipped");
		test.log(Status.INFO,result.getThrowable().getLocalizedMessage());
	}
	
	public void onFinish(ITestContext context) {
	    report.flush();
	  }
}
