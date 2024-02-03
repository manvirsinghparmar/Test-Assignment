package com.Assignment.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;

import com.Assignment.Browser.Browser;
import com.Assignment.Utils.ProxyDriver;

import comAssignment.env.EnviornmentUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver webDriver;
	public Properties prop;
	public static Logger logger;
	public static EventFiringWebDriver e_driver;
	public static Browser browser;
	private static final Browser DEFAULT_BROWSER = Browser.GOOGLE_CHROME;
	private static final EnviornmentUtils ENV = EnviornmentUtils.PRODUCTION;

	public TestBase() {
		
	}



	public void intialization() {

		// Manages the driver for the browser on which testing is performed
		switch (DEFAULT_BROWSER.getBrowsername()) {
		case "Chrome":
			webDriver = new ProxyDriver(WebDriverManager.chromedriver().create());
			break;
		case "Firefox":
			webDriver = new ProxyDriver(WebDriverManager.firefoxdriver().create());
			break;
		case "MicrosoftEdge":
			webDriver = new ProxyDriver(WebDriverManager.edgedriver().create());
			break;
		default:
			throw new IllegalArgumentException("Verify you pass the correct case");
		}

		/*
		 * Maximize the Browser window
		 */
		webDriver.manage().window().maximize();

		/*
		 * Delete all cookies
		 */
		webDriver.manage().deleteAllCookies();
	}

	public void quitBrowser() {
		/*
		 * Quite the Browser
		 */
		webDriver.quit();
	}

	public Browser getBrowser() {
		return Browser.getByName(System.getProperty("browser", DEFAULT_BROWSER.toString()));
	}
}
