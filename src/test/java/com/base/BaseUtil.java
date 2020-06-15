package com.base;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;




public class BaseUtil {

 

    public ExtentReports extent;
	public static Logger Log;

    public static ExtentTest scenarioDef;

    public static ExtentTest features;

    public static String reportLocation = ".\\EveronExtentResults";

}
