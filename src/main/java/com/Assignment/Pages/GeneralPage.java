package com.Assignment.Pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * This class makes use of Loadable component
 * class of selenium to ensure that page has loaded before 
 * any selenium action is performed
 */

public abstract class GeneralPage extends LoadableComponent<GeneralPage> {

	private static final int DEFAULT_TIME_TO_WAIT_FOR_PAGE = 50;
	protected WebDriver wd;

	public GeneralPage(WebDriver wd, boolean waitForPageToLoad) {

		this.wd = wd;
		if (waitForPageToLoad) {
			this.waitforPageToload();
		}
	}

	@Override
	protected void load() {
		String pageURL = getPageURL();
		wd.get(pageURL);

	}

	@Override
	public GeneralPage get() {

		GeneralPage page = super.get();
		this.waitforPageToload();
		return page;
	}

	@Override
	protected abstract void isLoaded() throws Error;

	protected abstract String getPageURL();

	public void waitforPageToload() {
		this.waitForDocumentCompletestState();

	}

	/*
	 * Method to confirm if the URL on the loaded page is as per expectation
	 * This ensure that proper page has loaded.
	 * 
	 */
	protected boolean urlContains(String url) {
		try {
			String pageUrl = getPageURL();

			URL pageUrlObject = new URL(pageUrl);
			URL urlObject = new URL(url);

			String pageUrlHost = pageUrlObject.getHost();
			String urlHost = urlObject.getHost();

			System.out.println((String.format("Checking URL: Contains: %s; Actual: %s", pageUrl, url)));

			if (pageUrlHost.equalsIgnoreCase(urlHost)) {
				String pageUrlExclHost = pageUrl.substring(pageUrl.indexOf(pageUrlHost) + pageUrlHost.length());
				String urlExclHost = url.substring(url.indexOf(urlHost) + urlHost.length());
				return urlExclHost.toLowerCase().contains(pageUrlExclHost.toLowerCase());
			} else {
				return false;
			}

		} catch (MalformedURLException e) {

		}

		return false;
	}

	public void waitForDocumentCompletestState() {
		waitForDocumentCompleteState(DEFAULT_TIME_TO_WAIT_FOR_PAGE);

	}

	// Method to ensure document/Web page is in ready state before selenium
	// interacts with it
	public void waitForDocumentCompleteState(int secondsToWait) {
		new WebDriverWait(wd, secondsToWait).until((ExpectedCondition<Boolean>) driver -> {
			while (true) {
				String readyState = getDocumentReadyState();
				if (readyState.equals("complete")) {
					return true;
				}
			}
		});

	}

	private String getDocumentReadyState() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) wd;
			String val = jse.executeScript("return document.readyState").toString();
			return val;
		} catch (WebDriverException e) {
			e.printStackTrace();
			return null;
		}
	}
}
