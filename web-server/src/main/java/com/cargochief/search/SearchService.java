package com.cargochief.search;

import java.util.List;

public interface SearchService {

	public List<SearchLoadResult> findListings(String origin, String destination, long fleetSize, long fleetAge);

}
