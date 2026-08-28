package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//a[normalize-space()='Modify your wish list']")
    WebElement msgHeading;

    @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logOut;

    public boolean isMyAccountPageExists() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            return wait.until(
                ExpectedConditions.visibilityOf(msgHeading)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
            ExpectedConditions.elementToBeClickable(logOut)
        ).click();
    }
}