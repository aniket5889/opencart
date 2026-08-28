package stepsDefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class Steps {
	
	WebDriver driver;
	
	@Given("the user is on the QAFox login page")
	public void the_user_is_on_the_nop_commerce_login_page() {
	    driver = new ChromeDriver();
	    
	    driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
	    
	    driver.findElement(By.xpath("//span[text()='My Account']")).click();
	    driver.findElement(By.xpath("//a[text()='Login']")).click();
	    
	}

	@When("the user enters valid credentials \\(username: {string}, password: {string})")
	public void the_user_enters_valid_credentials_username_password(String string, String string2) {
	    driver.findElement(By.id("input-email")).sendKeys(string);
	    driver.findElement(By.id("input-password")).sendKeys(string2);
	}

	@When("the user clicks the login button")
	public void the_user_clicks_the_login_button() {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
	}

	@Then("the user should be redirected to the dashboard page")
	public void the_user_should_be_redirected_to_the_my_account_page() {
	    driver.findElement(By.xpath("//h2[text()='My Account']")).isDisplayed();
	    driver.quit();
	}

	
	
}
