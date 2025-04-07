package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TS002_AccountLoginTest extends BaseClass{
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("******Starting TS002_AccountLoginTest******");
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExist();
		
		Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true, "Login Failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*****Finished TS002_AccountLoginTest*****");
		
		
	}

}
