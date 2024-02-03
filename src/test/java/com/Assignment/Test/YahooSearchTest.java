package com.Assignment.Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Assignment.Pages.GoogleSearchPage;
import com.Assignment.Pages.YahooSearchPage;
import com.Assignment.Utils.Utils;
import com.Assignment.base.TestBase;

public class YahooSearchTest extends TestBase {

	YahooSearchPage searchPage;

	@BeforeMethod
	public void launch() {
		intialization();
		searchPage = new YahooSearchPage(webDriver, false).get();
	}

	@Test
	public void firstResultTest() {
		var resultText = searchPage.getSearchResult("Canada");
		Assert.assertEquals(resultText.contains("Canada"), true);
	}

	@Test
	public void nonExistingResultTest() {
		var randomTextForSearch = Utils.generateRandomString(50);
		String resultText = searchPage.getSearchResult(randomTextForSearch);
		Assert.assertEquals(randomTextForSearch.concat(resultText), true);
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}
}
