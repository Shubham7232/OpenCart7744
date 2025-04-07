package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TS003_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class) //getting data provider from different class

	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		
	logger.info("******Starting TS003_LoginDDT******");
	Thread.sleep(3000);
	try
	{
	//HomePage
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	//LoginPage
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	//MyAccount
	MyAccountPage map=new MyAccountPage(driver);
	boolean targetPage=map.isMyAccountPageExist();
	
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(targetPage==true)
		{
			Assert.assertTrue(true);
			map.clickLogout();
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	if(exp.equalsIgnoreCase("Invalid"))
	{
		if(targetPage==true)
		{
			map.clickLogout();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	}
	catch (Exception e)
	{
		Assert.fail();
	}
	logger.info("*****Finished TS003_LoginDDT*****");
	}

}
