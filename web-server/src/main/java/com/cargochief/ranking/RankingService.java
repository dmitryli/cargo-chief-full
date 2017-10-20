package com.cargochief.ranking;

import java.util.List;

import com.cargochief.search.SearchLoadResult;

public interface RankingService {

	List<SearchLoadResult> rank(List<SearchLoadResult> list, long fleetSize, long fleetAge);

}