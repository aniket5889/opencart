package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",
          dataProviderClass = DataProviders.class,
          groups = "Datadriven", retryAnalyzer = utilities.RetryAnalyzer.class)
    public void verify_loginDDT(String email, String pwd, String exp) {

        logger.info("Test Execution Started for user: " + email);

        try {
            HomePage hp = new HomePage(getDriver());
            LoginPage lp = new LoginPage(getDriver());
            MyAccountPage macc = new MyAccountPage(getDriver());

            hp.clickMyAccount();
            hp.clickLogin();

            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            boolean targetPage = macc.isMyAccountPageExists();

            // VALID login scenario
            if (exp.equalsIgnoreCase("Valid")) {
                Assert.assertTrue(targetPage, "Login failed for valid credentials");
                macc.clickLogout();
            }

            // INVALID login scenario
            else if (exp.equalsIgnoreCase("Invalid")) {
                Assert.assertFalse(targetPage, "Login succeeded for invalid credentials");
            }

            logger.info("Test Execution Completed.");

        } catch (Exception e) {
            logger.error("Test Failed due to exception: ", e);
            Assert.fail(e.getMessage());
        }
    }
}
