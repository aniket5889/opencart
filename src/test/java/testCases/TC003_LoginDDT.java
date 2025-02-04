package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider = "LoginData", dataProviderClass=DataProviders.class, groups = "Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) {
		
		logger.info("Test Execution Started.");
		
		
		try {
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		MyAccountPage macc = new MyAccountPage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		boolean targetPage = macc.isMyAccountPageExists();
		Assert.assertTrue(targetPage);
		
		
		//Valid data Test Cases
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
			
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		logger.info("Test Execution Completed.");
		Thread.sleep(3000);
		}catch(Exception e) {
			Assert.fail();
		}
		
		
	}
	
}
