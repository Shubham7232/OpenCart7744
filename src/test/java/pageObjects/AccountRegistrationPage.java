package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage( WebDriver driver)
	{
		super(driver);
	}
	

@FindBy(xpath="//input[@placeholder='First Name']")
WebElement firstName;

@FindBy(xpath="//input[@placeholder='Last Name']") 
WebElement lastName;

@FindBy(xpath="//input[@type='email']") 
WebElement eMail;

@FindBy(xpath="//input[@type='tel']") 
WebElement telephone;

@FindBy(xpath="//input[@placeholder='Password']") 
WebElement password;

@FindBy(xpath="//input[@placeholder='Password Confirm']") 
WebElement passwordConfirm;

@FindBy(xpath="//input[@type='checkbox']") 
WebElement policy;

@FindBy(xpath="//input[@type='submit']") 
WebElement btncontinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") 
WebElement msgConfirm;

public void setFirstName(String fname) {
	firstName.sendKeys(fname);
}

public void setLastName(String lname) {
	lastName.sendKeys(lname);
}

public void setEmail(String email) {
	eMail.sendKeys(email);
}

public void setTelephone(String tel) {
	telephone.sendKeys(tel);
}

public void setPassword(String pwd) {
	password.sendKeys(pwd);
}

public void setPasswordConfirm(String pwdcfm) {
	passwordConfirm.sendKeys(pwdcfm);
}

public void clickAgree() {
	policy.click();
}

public void clickContinue() {
	btncontinue.click();
}

public String getConfirmationMsg() {
	try {
		return (msgConfirm.getText());
	}
	catch (Exception e)
	{
		return (e.getMessage());
	}
}

}
