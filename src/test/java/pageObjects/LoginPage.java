package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	

@FindBy(xpath="//input[@placeholder='E-Mail Address']") 
WebElement EmailAddress;

@FindBy(xpath="//input[@type='password']") 
WebElement txtPassword;

@FindBy(xpath="//input[@type='submit']") 
WebElement btnlogin;

   public void setEmail(String email) {
	   EmailAddress.sendKeys(email);
   }
	
   public void setPassword(String pwd) {
	   txtPassword.sendKeys(pwd);
   }
   
   public void clickLogin() {
	   btnlogin.click();
   }


}
