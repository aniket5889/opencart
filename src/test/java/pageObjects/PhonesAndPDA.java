package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhonesAndPDA extends BasePage{
	
	WebDriver driver;
	
	public PhonesAndPDA(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//h2[normalize-space()='Phones & PDAs']")
	WebElement txtPhonesAndPDA;
	
	@FindBy(xpath="//a[normalize-space()='HTC Touch HD']")
	WebElement lnkHTCTouchHD;
	
	@FindBy(xpath="//a[normalize-space()='Palm Treo Pro']")
	WebElement lnkPalmTreoPro;
	
	@FindBy(xpath="//a[normalize-space()='HTC Touch HD']/../..//button[@type='button']")
	WebElement btnHTCTouchHDAddToCart;
	
	@FindBy(xpath="//a[normalize-space()='Palm Treo Pro']/../..//button[@type='button']")
	WebElement btnPalmTreoProAddToCart;
	
	@FindBy(xpath="Added to your shopping cart")
	WebElement msgAddedToCart;	
	
	public String getPhonesAndPDAText() {
		return txtPhonesAndPDA.getText();
	}
	
	public void clickHTCTouchHD() {
		lnkHTCTouchHD.click();
	}
	
	public void clickPalmTreoPro() {
		lnkPalmTreoPro.click();
	}
	
	public void clickHTCTouchHDAddToCart() {
		btnHTCTouchHDAddToCart.click();
	}
	
	
	

}
