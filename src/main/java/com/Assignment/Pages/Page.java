package com.Assignment.Pages;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.Assignment.Browser.SearchEngine;


/*
 * This class is Base class of all page
 * classes
 */
public abstract class Page extends GeneralPage {

	public Page(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
		if (waitForPageToLoad) {
			this.waitForPageToLoad();
		}
	}

	public final static int DEFAULT_TIME_FOR_PAGE_TO_LOAD = 50;
	protected String wdWindowHandle;
	protected Map<String, String> query;
	
	private SearchEngine DEFAULT_SEARCH_ENGINE=SearchEngine.GOOGLE;
	
	private static final String GOOGLE_URL = "https://www.google.ca/";
	private static final String YAHOO_URL = "https://ca.yahoo.com/?p=us";

	protected String getDomain() {
	    switch (DEFAULT_SEARCH_ENGINE) {
	        case GOOGLE:
	            return GOOGLE_URL;
	        case YAHOO:
	            return YAHOO_URL;
	        default:
	            return GOOGLE_URL;
	    }
	}

	public String getPageURL() {
		return getDomain();
	}

	public void waitForPageToLoad() {
		this.isLoaded();
	}

	@Override
	public Page get() {
		super.get();
		this.waitForPageToLoad();
		return this;

	}

	@Override
	protected abstract void isLoaded();

}