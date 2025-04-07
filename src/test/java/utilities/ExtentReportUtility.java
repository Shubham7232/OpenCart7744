package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportUtility  implements ITestListener{
	public ExtentSparkReporter sparkReporter;    
	public ExtentReports extent;       
	public ExtentTest test;   
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());
		
		repName="Test-Report-" + timeStamp +	".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName); 
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");   //Titkle of report
		sparkReporter.config().setReportName("Opencart Functional Testing");   //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", os);
		
		List<String> includeGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());  
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ "Got Successfully Executed");  
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+ "Got Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		}catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+ "Got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir") +"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	 	
	/*try {
		URL url=new URL("file:///"+System.getProperty("user.dir")+ "\\reports\\"+repName);
		
		ImageHtmlEmail email=new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("nikamshubham47@gmail.com","shubham@7232"));
		email.setSSLOnConnect(true);
		email.setFrom("nikamshubham47@gmail.com");
		email.setSubject("Test Result");
		email.setMsg("Please find Attached Report....");
		email.addTo("shubhnikam7232@gmail.com");
		email.attach(url, "extent report", "Please check Report...");
		email.send();
	}
	catch(Exception e) {
		e.printStackTrace();
	}*/

}
}
