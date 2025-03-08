package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginTest_DDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups= {"datadriven","master"})
	public void loginWithData(String email,String password,String expresult) {
		
		logger.info("****Start TC02_LoginTest Started****");
		   try {
				HomePage homepage=new HomePage(driver);
				homepage.gotoMyAccount();
				homepage.gotoLoginPage();
				
				LoginPage loginpage=new LoginPage(driver);
				loginpage.setEmail(email);
				loginpage.setPassword(password);
				loginpage.clickLogin();
				
				MyAccountPage myaccountpage=new MyAccountPage(driver);
				Boolean display=myaccountpage.isMyAccountDisplay();
				
				if(expresult.equalsIgnoreCase("valid")) {
					if(display==true) {
						myaccountpage.clickLogout();
						Assert.assertTrue(true);
					}
					else {
						Assert.assertFalse(false);
					}
				}
				
				if(expresult.equalsIgnoreCase("invalid")) {
					if(display==true) {
						myaccountpage.clickLogout();
						Assert.assertTrue(false);
					}
					else {
						Assert.assertTrue(true);
					}
				}
				
			}
		   catch(Exception e) {
			   Assert.fail();
		   }
		   
		   logger.info("****TC02_LoginTest Finished****");
	}
	
}
