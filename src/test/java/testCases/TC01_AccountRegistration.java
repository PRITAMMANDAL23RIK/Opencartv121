package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegistration extends BaseClass {
	
	@Test(groups= {"sanity","master"})
	public void checkAccountCreation() {
		
		logger.info("**** TC01_AccountRegistration Started ****");
		
		try {
		HomePage homepage=new HomePage(driver);
		homepage.gotoMyAccount();
		logger.info("click on my account...");
		homepage.gotoRegistrationPage();
		logger.info("click on registration...");
		
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		logger.info("Provide user details....");
		arp.enterFirstname(getRandomString());
		arp.enterLastname(getRandomString());
		arp.enterEmail(getRandomString()+"@gmail.com");
		arp.enterTelephone(getRandomNumber());
		
		String pass=getRandomAlpahaNumeric();
		arp.enterPassword(pass);
		arp.enterConfirmPassword(pass);
		arp.clickCheckbox();
		arp.clickContinue();
		
		logger.info("Validate expected message....");
		String message=arp.getSuccessMessage();
		Assert.assertEquals(message, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.info("Test Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("**** TC01_AccountRegistration Finished ****");
	}
	
}
