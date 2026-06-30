package com.searchhub.dao;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SearchAnalytics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String searchQuery;
	private int resultCount;
	private long durationMs;
	private LocalDateTime searchedAt;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSearchQuery() {
		return searchQuery;
	}
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	public int getResultCount() {
		return resultCount;
	}
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	public long getDurationMs() {
		return durationMs;
	}
	public void setDurationMs(long duration) {
		this.durationMs = duration;
	}
	public LocalDateTime getSearchedAt() {
		return searchedAt;
	}
	public void setSearchedAt(LocalDateTime searchedAt) {
		this.searchedAt = searchedAt;
	}
	
	
}
