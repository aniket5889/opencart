package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseClass;

public class ExtentReportUtility implements ITestListener {

    // Thread-safe ExtentReports and ExtentTest
    private static ThreadLocal<ExtentReports> extent = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<String> reportPath = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        try {
            String browser = context.getCurrentXmlTest().getParameter("browser");
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            // Path for the report
            String path = System.getProperty("user.dir")
                    + "/reports/Report_" + browser + "_" + timeStamp + ".html";
            reportPath.set(path);

            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setReportName("OpenCart Automation - " + browser);
            spark.config().setDocumentTitle("Automation Report");

            ExtentReports ext = new ExtentReports();
            ext.attachReporter(spark);
            ext.setSystemInfo("Browser", browser);
            ext.setSystemInfo("OS", context.getCurrentXmlTest().getParameter("os"));
            ext.setSystemInfo("User", System.getProperty("user.name"));

            extent.set(ext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create thread-safe test instance
        ExtentTest extentTest = extent.get().createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        try {
            WebDriver driver = BaseClass.getDriver(); // Thread-safe WebDriver
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String path = System.getProperty("user.dir") + "/screenshots/"
                    + result.getName() + "_" + timeStamp + ".png";

            File dest = new File(path);
            Files.copy(source.toPath(), dest.toPath());

            test.get().addScreenCaptureFromPath(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.get().flush();

        try {
            Desktop.getDesktop().browse(new File(reportPath.get()).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
