package com.searchhub.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchhub.dto.SearchResultDTO;
import com.searchhub.service.SearchService;

@RestController
@RequestMapping("/api")
public class SearchController {

	private final SearchService searchService;
	
	public SearchController(SearchService service) {
		// TODO Auto-generated constructor stub
		this.searchService = service;
	}
	
	@GetMapping("search")
	public List<SearchResultDTO> getSearchResult(@RequestParam String query) throws Exception{
		return searchService.search(query);
	}
	
	
	
}
