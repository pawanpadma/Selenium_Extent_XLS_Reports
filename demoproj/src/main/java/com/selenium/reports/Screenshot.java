package com.selenium.reports;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;

import com.selenium.commons.CommonCode;

public class Screenshot extends TestListenerAdapter {
	
	public final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";	
	 public static ThreadLocal<RemoteWebDriver> driver = null;
	public DataSheet ds ;
	public CommonCode common;
	public int i=1;
	public Screenshot(){
		System.out.println("in screen shot class");
		ds = new DataSheet();
		
	}
	String testName=null;
	String group;
	
	@Override
	public void onStart(ITestContext context) {
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		ds.closeFile();
	}
	@Override
	public void onTestFailure(ITestResult tr) {
		System.out.println("value of testname is   " + testName);
		if (testName != tr.getMethod().getMethodName()) {
			Reporter.log("-Failed-", true);
			Reporter.log("Test method name is:- "
					+ tr.getMethod().getMethodName(), true);
			Reporter.log("Test name is:- "
					+ tr.getMethod().getConstructorOrMethod().getMethod()
							.getAnnotation(Test.class).testName(), true);
			group = Arrays.toString(
					tr.getMethod().getConstructorOrMethod().getMethod()
							.getAnnotation(Test.class).groups()).replace("[",
					"");
			group = group.replace("]", "");
			testName = tr.getMethod().getMethodName();
			System.out.println("value of testname is in if condition   " + testName);
			try {

				ds.setValueIntoCell("sheet1", 0, i, group);
				ds.setValueIntoCell("sheet1", 1, i,
						tr.getMethod().getConstructorOrMethod().getMethod()
								.getAnnotation(Test.class).testName());
				ds.setValueIntoCell("sheet1", 2, i,
						tr.getMethod().getConstructorOrMethod().getMethod()
								.getAnnotation(Test.class).description());
				ds.setValueIntoCell("sheet1", 3, i, "Fail");
				// i--;
				i++;
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		

		System.setProperty(ESCAPE_PROPERTY, "false");

		File file = new File("");
		Calendar lCDateTime = Calendar.getInstance();
		// System.out.println("Calender - Time in milliseconds :"+
		// lCDateTime.getTimeInMillis());
		String a = lCDateTime.getTimeInMillis() + ".jpg";
		Reporter.setCurrentTestResult(tr);
		try {
			System.out.println(file.getCanonicalPath());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String f = file.getCanonicalPath() + "\\target\\surefire-reports\\"
					+ lCDateTime.getTimeInMillis() + ".jpg";
			FileUtils.copyFile(scrFile, new File(f));
			Reporter.log("<a href=" + "../surefire-reports/" + a
					+ " target=\"_blank\">ScreenShot_" + tr.getName() + "</a>");

			Reporter.setCurrentTestResult(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	}
	@Override
	public void onTestSkipped(ITestResult tr) {
		Reporter.log("-Skipped-",true);
		Reporter.log("Test Method name is:- " + tr.getMethod().getMethodName(), true);
		Reporter.log("Test name is:- " +  tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(), true);
		try {
			String group=Arrays.toString(tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).groups()).replace("[", "");
			group=group.replace("]","");
			ds.setValueIntoCell("sheet1", 0, i,group);
			ds.setValueIntoCell("sheet1", 1, i, tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
			ds.setValueIntoCell("sheet1", 2, i, tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description());
			ds.setValueIntoCell("sheet1", 3, i, "Skip");
			i++;
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		if (testName == tr.getMethod().getMethodName()) {
			i--;
		}
		Reporter.log("-Passed-",true);
		Reporter.log("Test Method name is:- " + tr.getMethod().getMethodName(), true);
		Reporter.log("Test name is:- " +  tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(), true);
		Reporter.log("Test desc is:- " + tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description(), true);
		try {
			String group=Arrays.toString(tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).groups()).replace("[", "");
			group=group.replace("]","");
			ds.setValueIntoCell("sheet1", 0, i,group);
			ds.setValueIntoCell("sheet1", 1, i, tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
			ds.setValueIntoCell("sheet1", 2, i, tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description());
			ds.setValueIntoCell("sheet1", 3, i, "Pass");
			i++;
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		}
	
}
