package com.searchhub.dto;

public class SearchResultDTO {
	
	private String fileName;
	private String highlightedFragment;
	private float scroer;
	
	
	public SearchResultDTO(String fileName, String highlightedFragment, float scroer) {
		super();
		this.fileName = fileName;
		this.highlightedFragment = highlightedFragment;
		this.scroer = scroer;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getHighlightedFragment() {
		return highlightedFragment;
	}
	
	public float getScroer() {
		return scroer;
	}

	
}
