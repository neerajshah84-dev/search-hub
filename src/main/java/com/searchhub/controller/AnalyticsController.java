package com.searchhub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.searchhub.dto.AverageDurationDTO;
import com.searchhub.dto.DurationDistributionDTO;
import com.searchhub.dto.SearchSuccessRateDTO;
import com.searchhub.dto.SearchesTodayDTO;
import com.searchhub.dto.TopSearchDTO;
import com.searchhub.dto.TotalSearchesDTO;
import com.searchhub.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

	private final AnalyticsService analyticsService;
	
	public AnalyticsController(AnalyticsService analyticsService) {
		this.analyticsService =  analyticsService;
	}
	
	
	@GetMapping("/top-searches")
	public List<TopSearchDTO> getTopSearches() {
		return analyticsService.getTopSearches();
	}
	
	
	@GetMapping("/average-duration")
	public AverageDurationDTO getAverageDuration() {
		return analyticsService.getAverageDuration();
	}
	
	@GetMapping("/total-searches")
	public TotalSearchesDTO getTotalSearches() {
		return analyticsService.getTotalSearchDTO();
	}
	
	@GetMapping("/searches-today")
	public SearchesTodayDTO getSearchesToday() {
		return analyticsService.getSearchesToday();
	}
	
	@GetMapping("/zero-result-searches")
	public List<String> getZeroResultSearches() {
		return analyticsService.getZeroResultSearches();
	}
	
	@GetMapping("/duration-distribution")
	public DurationDistributionDTO getDurationDistribution() {
		return analyticsService.getDurationDistribution();
	}
	
	@GetMapping("/success-rate")
	public SearchSuccessRateDTO getSearchSuccessRate() {
		return analyticsService.getSearchSuccessRate();
	}
}
