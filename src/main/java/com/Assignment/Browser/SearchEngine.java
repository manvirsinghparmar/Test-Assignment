package com.Assignment.Browser;

public enum SearchEngine {

	GOOGLE("Google"), 
	YAHOO("Yahoo");

	private String searchEngine;

	SearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

	public String getSearchEngine() {
		return searchEngine;
	}

}
