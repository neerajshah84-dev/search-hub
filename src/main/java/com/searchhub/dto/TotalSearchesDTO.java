package com.searchhub.dto;

public class TotalSearchesDTO {
	
	private Long totalSearches;
	
	public TotalSearchesDTO(Long totalSearches) {
		this.totalSearches = totalSearches;
	}
	
	public Long getTotalSearches() {
		return totalSearches;
	}
}
