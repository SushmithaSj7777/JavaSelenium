package org.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    public static ExtentReports getReportObject()
    {

        String path = System.getProperty("user.dir")+"//Utility//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Web automation report");
        reporter.config().setReportName("Test Results");

        ExtentReports  extentReport=new ExtentReports();
        extentReport.attachReporter(reporter);
        extentReport.setSystemInfo("Tester", "Sush");
        return extentReport;


    }


}
