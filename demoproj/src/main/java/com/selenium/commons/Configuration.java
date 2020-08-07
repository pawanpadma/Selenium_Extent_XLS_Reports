package com.selenium.commons;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Configuration {

	public static Properties properties = new Properties(); // Here we imported
															// Java.util

	public static String filepath;
	public static FileInputStream in;
	public static String URL;

	public static String Browser;
	public static WebDriver webdriver;
	//public static WebDriver driver;
	public static String username;
	public static String password;

	public static WebDriver browser() {
		Reporter.log("in browser loop", true);

		if (Browser.equalsIgnoreCase("firefox")) {
			if (webdriver == null) {
				WebDriverManager.firefoxdriver().setup();
				webdriver = new FirefoxDriver();
				webdriver = new EventFiringWebDriver(webdriver).register(new WebDriverListner());
				 
				//WebDriverListner handler = new WebDriverListner();
				//driver.register(handler);
			} else
				return webdriver;

		}

		/*else if (Browser.equalsIgnoreCase("ie")) {
			if (webdriver == null) {
				String filepath = "src/test/resources/IEDriverServer.exe";
				filepath = System.getProperty("user.dir") + "/" + filepath;
				File file = new File(filepath);
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				webdriver = new InternetExplorerDriver();
			} else
				return webdriver;

		}*/ else if (Browser.equalsIgnoreCase("chrome")) {
			if (webdriver == null) {
				WebDriverManager.chromedriver().setup();

				webdriver = new ChromeDriver();
				webdriver = new EventFiringWebDriver(webdriver).register(new WebDriverListner());
				
			} else
				return webdriver;
		} /*else if (Browser.equalsIgnoreCase("safari")) {
			if (webdriver == null)
				webdriver = new SafariDriver();
			return webdriver;
		}*/
		return webdriver;

	}

	static {
		try {
			if (System.getProperty("EnvType") == null) {
				filepath = "src/test/resources/QA-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			} else if (System.getProperty("EnvType").equalsIgnoreCase("qa")) {
				filepath = "src/test/resources/QA-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			} else if (System.getProperty("EnvType").equalsIgnoreCase("dev")) {
				filepath = "src/test/resources/dev-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			} else if (System.getProperty("EnvType").equalsIgnoreCase("staging")) {
				filepath = "src/test/resources/staging-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		URL = properties.getProperty("url");

		Browser = properties.getProperty("Browser");
		username = properties.getProperty("username");
		password = properties.getProperty("password");

	}

	public static String LoginURL() {
		Reporter.log("URL is" + URL, true);
		return URL;

	}

	/*
	 * public WebDriver getDriver() { // TODO Auto-generated method stub
	 * 
	 * return driver; }
	 */

}
