package com.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;
import com.selenium.reports.ExtentManager;
import com.selenium.reports.ExtentTestListner;
import com.selenium.reports.ExtentTestManager;

public class HomePage {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;
	//ITestResult result;
	public ExtentTestManager extent =new ExtentTestManager();

	@FindBy(className = "login")
	private WebElement signInLink;

	@FindBy(className = "logout")
	private WebElement logoutLink;

	@FindBy(className = "logo")
	private WebElement homeLogo;

	@FindBy(id = "search_query_top")
	private WebElement searchTextBox;

	@FindBy(name = "submit_search")
	private WebElement searchIcon;

	public HomePage() {
		PageFactory.initElements(driver, this);
		read = new ReadExcel();
	}

	public void tapSignin() {
		
		signInLink.click();
		Assert.assertEquals(driver.getTitle(), read.readData("Login_Title"));

	}

	public void validateHome() {
		Assert.assertEquals(driver.getTitle(), read.readData("Home_Title"));
	}

	public void logoutOfAPP() {
		extent.logStatus("hello");
		//ExtentTestManager.getTest().info("hello");
		logoutLink.click();
	}

	public void navigateToHome() {
		homeLogo.click();
	}

	public void searchProduct(String productName,String productResult,String price ) {
		extent.logStatus("another log");
		searchTextBox.clear();
		searchTextBox.sendKeys(productName);
		searchIcon.click();
		WebElement e=driver.findElement(By.xpath("//img[@title='"+productName+"']"));
		Actions a= new Actions(driver);
		a.moveToElement(e).build().perform();
		driver.findElement(By.xpath("//h5[@itemprop='name']//a[contains(text(),'"+productResult+"')]")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='left-block']//div[@class='content_price']//span[contains(text(),'"+price+"')]")).isDisplayed();
		
		
		
	}

}
