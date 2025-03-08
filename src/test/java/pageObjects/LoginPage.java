package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement textEmail;
	
	@FindBy(id="input-password")
	WebElement textPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	public void setEmail(String email) {
		textEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		textPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	

}
