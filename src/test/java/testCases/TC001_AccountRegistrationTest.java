package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups= {"Regression", "Master"})
	public void Verify_account_registration() throws InterruptedException
	{
		logger.info("******** Starting TC001_AccountRegiistrationTest ********");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account Link");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		Thread.sleep(3000);
		AccountRegistrationPage rp=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details....");
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		rp.setTelephone(randomNumber());
		
		//String password=Alphanumeric@random();
		
		String password=randomAlphanumeric();
		
		rp.setPassword(password);
		rp.setPasswordConfirm(password);
		
		rp.clickAgree();
		rp.clickContinue();
		
		Thread.sleep(3000);
		
		logger.info("Validating expected Message...");
		String confmsg=rp.getConfirmationMsg();		
	    Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch (Exception e)
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("******** Finishing TC001_AccountRegiistrationTest ********");
	
	}
	
}
