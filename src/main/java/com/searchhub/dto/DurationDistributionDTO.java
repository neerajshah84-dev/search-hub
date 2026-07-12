package com.searchhub.dto;

public class DurationDistributionDTO {
	
	private Long minDurationMs;
	private Double avgDurationMs;
	private Long maxDurationMs;
	
	
	public DurationDistributionDTO(Long minDurationMs, Double avgDurationMs, Long maxDurationMs) {
		
		this.minDurationMs = minDurationMs;
		this.avgDurationMs = avgDurationMs;
		this.maxDurationMs = maxDurationMs;
		
	}
	
	public Long getMinDurationMs() {
		return minDurationMs;
	}
	
	public Double getAvgDurationMs() {
		return avgDurationMs;
	}
	
	public Long getMaxDurationMs() {
		return maxDurationMs;
	}
	
}
