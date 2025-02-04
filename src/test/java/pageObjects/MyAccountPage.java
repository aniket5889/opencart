package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//a[normalize-space()='Modify your wish list']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logOut;
	
	public boolean isMyAccountPageExists() {
		try {
		return(msgHeading.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		logOut.click();
	}
	
	

	

}
