package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseClass {

    protected Logger logger;
    protected Properties p;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browserName = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static String getBrowserName() {
        return browserName.get();
    }

    @BeforeMethod(groups = {"Sanity", "Regression", "Master", "Datadriven"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        browserName.set(br);

        FileReader file = new FileReader(".//src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        WebDriver localDriver = null;

        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setPlatform(
                os.equalsIgnoreCase("windows") ? Platform.WIN11 :
                os.equalsIgnoreCase("mac") ? Platform.MAC :
                Platform.LINUX
            );

            cap.setBrowserName(
                br.equalsIgnoreCase("edge") ? "MicrosoftEdge" : br
            );

            localDriver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), cap);
        } else {
            switch (br.toLowerCase()) {
                case "chrome": localDriver = new ChromeDriver(); break;
                case "edge": localDriver = new EdgeDriver(); break;
                case "firefox": localDriver = new FirefoxDriver(); break;
                default: throw new RuntimeException("Invalid browser");
            }
        }

        driver.set(localDriver);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(p.getProperty("appURL"));
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            browserName.remove();
        }
    }

    // ================= UTIL METHODS =================

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphanumeric() {
        return RandomStringUtils.randomAlphabetic(4) + "@" +
               RandomStringUtils.randomNumeric(3);
    }

    // Screenshot (Thread Safe)
    public static String captureScreen(String testName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir")
                + "/screenshots/" + testName + "_" + timeStamp + ".png";

        File dest = new File(path);
        src.renameTo(dest);

        return path;
    }
}
