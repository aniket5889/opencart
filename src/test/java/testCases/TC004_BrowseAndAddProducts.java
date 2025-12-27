package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC004_BrowseAndAddProducts extends BaseClass {
	
	@Test(groups = {"Regression", "Master"}, retryAnalyzer = utilities.RetryAnalyzer.class)
	public void verify_browseAndAddProducts() {
		logger.info("....Starting TC004 Browse And Add Products Test....");
		
		try {
		//Page Object Instantiation
		HomePage hp = new HomePage(getDriver());
		LoginPage lp = new LoginPage(getDriver());
		MyAccountPage macc = new MyAccountPage(getDriver());
		
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
		
		hp.clickHomePage();
		hp.clickAddToCart();
		hp.isSuccessMessageExists();
		
		
		logger.info("....Finished TC004 Browse And Add Products Test....");
		}catch(Exception e) {
			Assert.fail();
		}
		
	}

}
