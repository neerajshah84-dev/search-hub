package com.searchhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searchhub.dao.SearchAnalytics;

@Repository
public interface SearchAnalyticsRepository 
	extends JpaRepository<SearchAnalytics, Long>{

}
