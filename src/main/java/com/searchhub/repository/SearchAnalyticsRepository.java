package com.searchhub.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.searchhub.dao.SearchAnalytics;
import com.searchhub.dto.AverageDurationDTO;
import com.searchhub.dto.DurationDistributionDTO;
import com.searchhub.dto.SearchSuccessRateDTO;
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
	
	@Query("""
			SELECT new com.searchhub.dto.DurationDistributionDTO(
				MIN(sa.durationMs),
				AVG(sa.durationMs),
				MAX(sa.durationMs)
			) FROM SearchAnalytics sa
			""")
	DurationDistributionDTO findDurationDistribution();
	
//	Successful Searches = resultCount > 0
//	Failed Searches     = resultCount == 0
//	Success Rate = (successful / total) × 100
	@Query("""
			SELECT new com.searchhub.dto.SearchSuccessRateDTO(
				COUNT(sa.id),
				SUM(CASE WHEN sa.resultCount > 0 THEN 1L ELSE 0L END),
				SUM(CASE WHEN sa.resultCount = 0 THEN 1L ELSE 0L END),
				CASE
					WHEN COUNT (sa.id) = 0 THEN 0.0
					ELSE (
						(SUM(
						CASE WHEN sa.resultCount > 0 THEN 1.0 ELSE 0.0 END) / COUNT(sa.id )) * 100.0
						
					)
				END
			)
			FROM SearchAnalytics sa
			""")
	SearchSuccessRateDTO findSearchSuccessRate();
}
