package com.searchhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.searchhub.dao.SearchAnalytics;
import com.searchhub.dto.AverageDurationDTO;
import com.searchhub.dto.TopSearchDTO;

@Repository
public interface SearchAnalyticsRepository 
	extends JpaRepository<SearchAnalytics, Long>{

	@Query("""
			SELECT new com.searchhub.dto.TopSearchDTO(sa.searchQuery, COUNT(sa.id)) 
			FROM SearchAnalytics sa 
			GROUP BY sa.searchQuery ORDER BY COUNT(sa.id) DESC
			""")
	List<TopSearchDTO> findTopSearches();
	
	
	@Query("""
			SELECT new com.searchhub.dto.AverageDurationDTO(
				AVG(sa.durationMs)
				) 
			FROM SearchAnalytics sa
			""")
	AverageDurationDTO findAverageDuration();
}
