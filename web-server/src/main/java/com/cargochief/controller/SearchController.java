package com.cargochief.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cargochief.search.SearchLoadResult;
import com.cargochief.search.SearchPresentationResult;
import com.cargochief.search.SearchService;

/*
 * Dima: this class will invoke a search on data
 * parameters:
 * origin = comma separated city, state
 * destination = comma separated city, state
 * fleet-size = integer
 * fleet-age = integer
 * 
 * Sample query:
 * 
 * http://localhost:8080/search?origin=oakland,ca&destination=los%20angeles,CA&fleet-size=10&fleet-age=15
 * 
 * 
 */
@RestController
public class SearchController {
	
	private final static Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private SearchService searchService = null;
	
	@RequestMapping(path = "/search", method = RequestMethod.GET, produces = "application/json")
	public List<SearchPresentationResult> search(@RequestParam("origin") String origin, @RequestParam("destination") String destination,
			@RequestParam("fleet-size") long fleetSize, @RequestParam("fleet-age") long fleetAge) {
		log.info("Executing search with params:");
		
		log.info("origin " + origin + " destination "+ destination + " fleet size " + fleetSize + " fleet age " + fleetAge);
		
		List<SearchLoadResult> listings = searchService.findListings(origin, destination, fleetSize, fleetAge);
		return convert(listings);
	}

	private List<SearchPresentationResult> convert(List<SearchLoadResult> listings) {
		List<SearchPresentationResult>  returnList = new ArrayList<>();
		
		int i = 0;
		for (SearchLoadResult slr : listings) {
			i++;
			
			SearchPresentationResult spr = new SearchPresentationResult();
			
			spr.setCarrierMC(slr.getCarrierMc());
			spr.setCarrierName(slr.getCarrierName());
			spr.setDho(slr.getDho());
			spr.setFleetAge(slr.getFleetAge());
			spr.setFleetSize(slr.getFleetSize());
			spr.setListingAge(slr.getListingAge());
			spr.setMatchScore(i);
			spr.setTruckOrigin(slr.getFullOrigin());
			
			returnList.add(spr);
		}
		
		return returnList;
	}
}
