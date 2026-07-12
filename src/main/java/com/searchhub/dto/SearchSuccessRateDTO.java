package com.searchhub.dto;

public class SearchSuccessRateDTO {
	
	private Long totalSearches;
	private Long successfullSearches;
	private Long failedSearches;
	private Double successRate;
	
	public SearchSuccessRateDTO(Long totalSearch, Long successfulSearch, Long failedSearch, Double successRate) {
		this.totalSearches = totalSearch;
		this.successfullSearches = successfulSearch;
		this.failedSearches = failedSearch;
		this.successRate = successRate;
	}
	
	
	public Long getTotalSearches() {
		return totalSearches;
	}

	public Long getSuccessfullSearches() {
		return successfullSearches;
	}

	public Long getFailedSearches() {
		return failedSearches;
	}

	public Double getSuccessRate() {
		return successRate;
	}
	
}
