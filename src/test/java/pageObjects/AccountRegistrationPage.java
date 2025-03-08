package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="input-firstname")
	WebElement txtfirstname;
	
	@FindBy(id="input-lastname")
	WebElement txtlastname;
	
	@FindBy(id="input-email")
	WebElement txtemail;
	
	@FindBy(id="input-telephone")
	WebElement txttelephone;
	
	@FindBy(id="input-password")
	WebElement txtpassword;
	
	@FindBy(id="input-confirm")
	WebElement txtconfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement boxcheck;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnconfirm;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement successmessge;
	
	public void enterFirstname(String name) {
		txtfirstname.sendKeys(name);
	}
	
	public void enterLastname(String title) {
		txtlastname.sendKeys(title);
	}
	
	public void enterEmail(String email) {
		txtemail.sendKeys(email);
	}
	
	public void enterTelephone(String phone) {
		txttelephone.sendKeys(phone);
	}
	
	public void enterPassword(String password) {
		txtpassword.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmpass) {
		txtconfirmpassword.sendKeys(confirmpass);
	}
	
	public void clickCheckbox() {
		boxcheck.click();
	}
	
	public void clickContinue() {
		btnconfirm.click();
	}
	
	public String getSuccessMessage() {
		try {
			return successmessge.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}

}
