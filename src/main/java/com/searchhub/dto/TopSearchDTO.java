package com.searchhub.dto;

public class TopSearchDTO {
	
	private String query;
	private Long searchCount;
	
	public TopSearchDTO(String query , Long searchCount) {
		this.query = query;
		this.searchCount = searchCount;
		
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Long getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(Long count) {
		this.searchCount = count;
	}
	
	
}
