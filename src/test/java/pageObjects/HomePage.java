package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement register;
	
	@FindBy(xpath="(//a[text()='Login'])[1]")
	WebElement login;
	
	public void gotoMyAccount() {
		myAccount.click();
	}
	
	public void gotoRegistrationPage() {
		register.click();
	}
	
	public void gotoLoginPage() {
		login.click();
	}

}
