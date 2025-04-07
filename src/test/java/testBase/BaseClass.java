package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
	    
		
		
		//Loading config.properties file
		FileReader file=new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		default : System.out.println("Invalid Browser name...");; return;
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));   //reading url from properties file.
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression", "Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String gString=RandomStringUtils.randomAlphabetic(5);
		return gString;
	}
	
	public String randomNumber()
	{
		String gString=RandomStringUtils.randomNumeric(10);
		return gString;
	}
	
	public String randomAlphanumeric()
	{
		String gString=RandomStringUtils.randomAlphabetic(3);
		String gNumber=RandomStringUtils.randomNumeric(3);
		return gString+"@"+gNumber;
	}
	
	public String captureScreen(String tname) throws IOException
	{
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File sourcefile=ts.getScreenshotAs(OutputType.FILE);
		
		String  targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\" + tname + "-" + timeStamp + ".png";
				
		File targetFile=new File(targetFilePath);
		
		sourcefile.renameTo(targetFile);
		
		return targetFilePath;
				
	}
	


}
