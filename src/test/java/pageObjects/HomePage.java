package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath="//a[normalize-space()='Qafox.com']")
	WebElement HomePage;
	
	@FindBy(xpath="//button[@onclick=\"cart.add('43');\"]")
	WebElement btnAddToCart;
	
	@FindBy(xpath="//div[@class=\"alert alert-success alert-dismissible\"]")
	WebElement msgSuccess;
	
	public void clickMyAccount() {
		lnkAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
	public void clickHomePage() {
		HomePage.click();
	}
	
	public void clickAddToCart() {
		btnAddToCart.click();
	}
	
	public boolean isSuccessMessageExists() {
		try {
		return(msgSuccess.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
	

}
