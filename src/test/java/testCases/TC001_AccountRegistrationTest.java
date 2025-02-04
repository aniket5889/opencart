package testCases;


import org.junit.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups = {"Sanity", "Master"})
	public void verify_account_registration() {
		
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try {
			HomePage hp = new HomePage(driver);
			
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link..");
			
			hp.clickRegister();
			logger.info("Clicked on Register Link..");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("Entering Customer Details....");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString() + "@gmail.com");
			regpage.setTelephone(randomNumber());
			
			String password = randomAlphanumeric();
			
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			
			logger.info("Validating expected message..");
			String configMsg = regpage.getConfirmationMsg();
			
			if(configMsg.equals("Your Account Has Been Created!")) {
				
				Assert.assertTrue(true);
				
			}else {
				
				logger.error("Test Failed....");
				logger.debug("Debug Logs...");
				Assert.assertFalse(false);
			}
		}catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("**** TC001_AccountRegistrationTest Completed.....");
	}
	
	
	
}
