package com.Assignment.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Assignment.Utils.ProxyDriver;

public class YahooSearchPage extends SearchPage {
	
	private final By searchInputField = By.id("ybar-sbq");
	private final By firstResult = By.cssSelector("div#web h3 a,div#web strong");

	public YahooSearchPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}
	
	@Override
	public YahooSearchPage get() {
		return (YahooSearchPage) super.get();
	}

	@Override
	public String getSearchResult(String searchTerm) {
		performSearch(searchTerm);
		return ((ProxyDriver) wd).getText(firstResult);
	}

	private void performSearch(String searchTerm) {
		((ProxyDriver) wd).sendKeys(searchInputField, searchTerm);
		((ProxyDriver) wd).pressEnterKey(searchInputField);
	}

}
