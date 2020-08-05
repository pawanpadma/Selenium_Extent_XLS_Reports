package com.selenium.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {


    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    static ExtentReports extent = ExtentManager.getInstance();
    public static ExtentTest test;

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
    	
        extent.flush();
    }

    public static synchronized ExtentTest startTest(String testName) {
    	test = extent.createTest(testName);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
    
    public void logStatus (String msg){

        // Log test status
        test.info(msg);
    }
    
    public void logError (Throwable msg){

        // Log test status
        test.error(msg);
    }

}
