package com.searchhub.service;



import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.searchhub.dao.SearchAnalytics;
import com.searchhub.dto.AverageDurationDTO;
import com.searchhub.dto.TopSearchDTO;
import com.searchhub.repository.SearchAnalyticsRepository;

@Service
public class AnalyticsService {
	
	private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);

	private final SearchAnalyticsRepository searchAnalyticsRepository;
	
	public AnalyticsService(SearchAnalyticsRepository searchAnalyticsRepository) {
		
		this.searchAnalyticsRepository = searchAnalyticsRepository;
	}
	
	public void saveSearchAnalytics(String searchQuery, int resultCount, long durationMs) {
		SearchAnalytics searchAnalytics = new SearchAnalytics();
		searchAnalytics.setDurationMs(durationMs);
		searchAnalytics.setResultCount(resultCount);
		searchAnalytics.setSearchedAt(LocalDateTime.now());
		searchAnalytics.setSearchQuery(searchQuery);
		
		searchAnalyticsRepository.save(searchAnalytics);
		
	}
	
	public List<TopSearchDTO> getTopSearches() {
		return searchAnalyticsRepository.findTopSearches();
	}
	
	public AverageDurationDTO getAverageDuration( ) {
		return searchAnalyticsRepository.findAverageDuration();
	}
}
