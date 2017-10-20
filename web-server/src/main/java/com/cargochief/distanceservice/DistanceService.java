package com.cargochief.distanceservice;

import java.util.List;

import com.cargochief.search.SearchLoadResult;

public interface DistanceService {

	public List<SearchLoadResult> calculateDistanceMatrixWithResults(List<SearchLoadResult> list,
			String[] destinations);

}