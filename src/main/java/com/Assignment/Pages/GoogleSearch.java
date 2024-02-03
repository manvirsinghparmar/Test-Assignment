package com.Assignment.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Assignment.Utils.ProxyDriver;

public class GoogleSearch extends SearchPage {

	private final By searchInputField = By.cssSelector("textarea[name='q']");
	private final By firstResult = By.cssSelector("h3");

	public GoogleSearch(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}
	
	@Override
	public GoogleSearch get() {
		return (GoogleSearch) super.get();
	}

	@Override
	public String getFirstSearchResult(String searchTerm) {
		performSearch(searchTerm);
		return ((ProxyDriver) wd).getText(firstResult);
	}

	private void performSearch(String searchTerm) {
		((ProxyDriver) wd).sendKeys(searchInputField, searchTerm);
		((ProxyDriver) wd).pressEnterKey(searchInputField);
	}

}
