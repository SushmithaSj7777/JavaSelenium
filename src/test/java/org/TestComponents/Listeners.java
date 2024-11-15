package org.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.Resources.ExtentReport;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.imageio.IIOException;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;

    ExtentReports extentReport = ExtentReport.getReportObject();
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        test =extentReport.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        test.log(Status.PASS,"Test passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        test.fail(result.getThrowable());
        try {
            driver = ((WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String filepath = null;
        try {
            filepath = takescreenshotwhentestcasefail(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(filepath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // This method is rarely used, but it's available if needed
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
        extentReport.flush();
    }



}
