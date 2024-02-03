package com.Assignment.Pages;

import org.openqa.selenium.WebDriver;

public abstract class SearchPage extends Page {

	public SearchPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	@Override
	protected void isLoaded() {
		if (!urlContains(wd.getCurrentUrl())) {
			throw new Error();
		}
	}

	public abstract String getSearchResult(String query);

}
