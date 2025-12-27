package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseClass;

public class ExtentReportUtility implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private String reportPath;

    @Override
    public void onStart(ITestContext context) {

        String browser = BaseClass.getBrowserName();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        reportPath = System.getProperty("user.dir")
                + "/reports/Report_" + browser + "_" + time + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("OpenCart Automation - " + browser);
        spark.config().setDocumentTitle("Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Browser", browser);
        extent.setSystemInfo("OS",
                context.getCurrentXmlTest().getParameter("os"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(
                result.getMethod().getMethodName());
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
            String path = BaseClass.captureScreen(result.getName());
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
        extent.flush();

        try {
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
