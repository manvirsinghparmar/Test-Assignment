package com.Assignment.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Assignment.Utils.ProxyDriver;

public class GoogleSearchPage extends SearchPage {

	private final By searchInputField = By.cssSelector("textarea[name='q']");
	private final By firstResult = By.cssSelector("div#rso h3,div.card-section span em");

	public GoogleSearchPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	@Override
	public GoogleSearchPage get() {
		return (GoogleSearchPage) super.get();
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
