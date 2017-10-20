package com.cargochief.repository;

import java.util.List;

import com.cargochief.search.SearchLoadResult;

/*
 * Dima: This is an interface that will access the database using JPA to fetch info from CC_CARRIER_INFO
 */

public interface SearchRepository {

	public List<SearchLoadResult> findPotentialMatchesBasedOnOriginAndDestiation(String originCity, String originState, String originZip,
			String destCity, String destState, String destZip);
	
	public List<SearchLoadResult> findAllInfoNoOriginDestinationRestrictions();
}
