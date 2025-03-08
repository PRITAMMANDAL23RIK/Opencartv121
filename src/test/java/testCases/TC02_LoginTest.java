package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass {
	
	@Test(groups= {"regression","master"})
	public void checkLogin() {
		
	   logger.info("****Start TC02_LoginTest Started****");
	   try {
			HomePage homepage=new HomePage(driver);
			homepage.gotoMyAccount();
			homepage.gotoLoginPage();
			
			LoginPage loginpage=new LoginPage(driver);
			loginpage.setEmail(prop.getProperty("userId"));
			loginpage.setPassword(prop.getProperty("password"));
			loginpage.clickLogin();
			
			MyAccountPage myaccountpage=new MyAccountPage(driver);
			Boolean display=myaccountpage.isMyAccountDisplay();
			
			Assert.assertEquals(true, display);
		}
	   catch(Exception e) {
		   Assert.fail();
	   }
	   
	   logger.info("****TC02_LoginTest Finished****");
		
	}

}
