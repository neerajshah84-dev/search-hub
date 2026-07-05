package com.searchhub.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.searchhub.dao.SearchAnalytics;
import com.searchhub.dto.AverageDurationDTO;
import com.searchhub.dto.SearchesTodayDTO;
import com.searchhub.dto.TopSearchDTO;
import com.searchhub.dto.TotalSearchesDTO;

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
	
	
	@Query("""
			SELECT new com.searchhub.dto.TotalSearchesDTO(
				COUNT(sa.id)
			)
			FROM SearchAnalytics sa
			""")
	
	TotalSearchesDTO findTotalSearches();
	
	
	@Query("""
			SELECT new com.searchhub.dto.SearchesTodayDTO (
				COUNT(sa.id)
			)
			FROM SearchAnalytics sa
			WHERE sa.searchedAt >= :startOfDay
			""")
	SearchesTodayDTO  findSearchesToday(LocalDateTime startOfDay);
	
	
	@Query("""
			SELECT DISTINCT sa.searchQuery
			FROM SearchAnalytics sa
			WHERE sa.resultCount = 0 ORDER BY sa.searchQuery
			
			""")
	List<String> findZeroResultSearches();
}
