package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;




public class ExtentReportUtil extends BaseUtil {

    String fileName = reportLocation + "extentreport.html";


    public void ExtentReport() {
        //First is to create Extent Reports
        extent = new ExtentReports();

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Test report APITesting");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Test report");

        extent.attachReporter(htmlReporter);

    }

   

    public void FlushReport(){
        extent.flush();
    }




}