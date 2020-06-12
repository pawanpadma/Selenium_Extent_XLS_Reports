package com.automationpractice.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.SummaryPage;
import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;
import com.selenium.reports.ExtentTestListner;
import com.selenium.reports.Screenshot;


@Listeners({ExtentTestListner.class,Screenshot.class})
public class SmokeTest {

	public WebDriver driver = Configuration.browser();
	public HomePage home;
	public LoginPage login;
	public SummaryPage summary;

	public ReadExcel read;

	public SmokeTest() {
		home = new HomePage();
		login = new LoginPage();
		summary = new SummaryPage();
		read = new ReadExcel();
		
	}

	@BeforeSuite(alwaysRun = true)
	public void invokeBrowser() {
		
		driver.get(Configuration.URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		home.tapSignin();
		login.loginToAPP(Configuration.username, Configuration.password);
		home.validateHome();
	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void navigateToHome() {
		home.navigateToHome();
	}

	@Test(testName = "login_Negative", description = "login_Negative", timeOut = 190000, enabled = true, groups = {
			"sanity", "1" }, priority = 1)
	public void login_Negative() {
		home.logoutOfAPP();
		home.tapSignin();
		login.loginToAPP("sdsad", "sfsdfsd");
		login.validateError();
		login.loginToAPP(Configuration.username, Configuration.password);
		home.validateHome();
	}

	@Test(testName = "login_Positive", description = "login to app with valid credentials", timeOut = 190000, enabled = true, groups = {
			"sanity", "2" }, priority = 2)
	public void login_positive() {
		Assert.assertTrue(false);
		/*
		 * home.logoutOfAPP(); home.tapSignin();
		 * login.loginToAPP(Configuration.username, Configuration.password);
		 * home.validateHome();
		 */

	}

	@Test(testName = "logout", description = "logout of the application", timeOut = 190000, enabled = true, groups = {
			"sanity", "3" })
	public void logout() {

		home.logoutOfAPP();
		home.tapSignin();
		Assert.assertEquals(driver.getTitle(), read.readData("Login_Title"));
		login.loginToAPP(Configuration.username, Configuration.password);
		home.validateHome();

	}

	@Test(testName = "searchProduct", description = "search the product", timeOut = 190000, enabled = true, groups = {
			"sanity", "4" })
	public void searchProduct() {

		home.searchProduct("Faded Short Sleeve T-shirts", "Faded Short Sleeve T-shirts", "$16.51");

	}

}
