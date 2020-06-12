package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.selenium.commons.Configuration;

public class SummaryPage {
	
	public WebDriver driver=Configuration.browser();
	
	public SummaryPage() {
		PageFactory.initElements(driver, this);
	}

}
