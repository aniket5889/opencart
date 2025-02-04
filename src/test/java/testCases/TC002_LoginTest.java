package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void verify_login() {
		logger.info("....Starting TC002 Login Test....");
		
		try {
		//Page Object Instantiation
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		MyAccountPage macc = new MyAccountPage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		logger.info("....Entering  Email & Password....");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		
		logger.info("....Clicked Login Button....");
		lp.clickLogin();
		
		//MyAccount
		boolean targetPage = macc.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);
		
		logger.info("....Finished TC002 Login Test....");
		}catch(Exception e) {
			Assert.fail();
		}
		
	}

}
