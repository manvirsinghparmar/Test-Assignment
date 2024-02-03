package com.Assignment.Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Assignment.Pages.GoogleSearch;
import com.Assignment.base.TestBase;

public class GoogleSearchTest extends TestBase {

	GoogleSearch searchPage;

	@BeforeMethod
	public void launch() {
		intialization();
		searchPage = new GoogleSearch(webDriver, false).get();
	}
	
	@Test
	public void validate() {
		String result=searchPage.getFirstSearchResult("Java");
		Assert.assertEquals("Expected First Result Title", result);
	}
	
	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}
}
