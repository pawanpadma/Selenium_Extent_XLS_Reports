package com.selenium.commons;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class CommonCode {
	public WebDriver driver = Configuration.browser();

	public void getWindowHandle() {
		Set<String> handles = driver.getWindowHandles();
		if (handles.size() >= 1) {
			System.out.println("Number of broiwsers opened are" + handles.size());
			for (String handle : handles) {
				driver.switchTo().window(handle);

			}
		}

	}

	public WebElement fluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(1)).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return foo;
	};

}
