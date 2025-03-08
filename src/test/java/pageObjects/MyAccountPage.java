package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement getText;
	
	@FindBy(xpath="(//a[text()='Logout'])[2]")
	WebElement logout;
	
	public boolean isMyAccountDisplay() {
		try {
			return getText.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		logout.click();
	}

}
