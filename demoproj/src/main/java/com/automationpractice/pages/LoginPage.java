package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class LoginPage {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;

	@FindBy(id = "email")
	public WebElement emailTextBox;

	@FindBy(id = "passwd")
	public WebElement passwordTextBox;

	@FindBy(id = "SubmitLogin")
	public WebElement loginBtn;
	
	@FindBy(xpath = "//ol//li")
	public WebElement errorMessage;
	
	

	public LoginPage() {
		PageFactory.initElements(driver, this);
		read= new ReadExcel();
	}

	public void loginToAPP(String username, String password) {
		emailTextBox.clear();
		emailTextBox.sendKeys(username);
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
		loginBtn.click();

	}
	
	public void validateError() {
		Assert.assertEquals(errorMessage.getText(), read.readData("Login_Error"));
	}

}
