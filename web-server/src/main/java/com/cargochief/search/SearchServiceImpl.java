package com.cargochief.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cargochief.distanceservice.DistanceService;
import com.cargochief.ranking.RankingService;
import com.cargochief.repository.SearchRepository;

@Service
public class SearchServiceImpl implements SearchService {

	private final static int LIST_CHUNK = 12;

	@Autowired
	private SearchRepository searchRepository = null;

	@Autowired
	private DistanceService distanceService = null;
	
	@Autowired
	private RankingService rankingService = null;

	private final static Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

	public List<SearchLoadResult> findListings(String origin, String destination, long fleetSize, long fleetAge) {

		// if the format city,state is not honored, throw an error
		if (!origin.contains(",") || !destination.contains(",")) {
			throw new RuntimeException("The format for origin or destination of city,state is incorrect");
		}

		// parse the paramenters
		String res[] = origin.split(",");
		String originCity = res[0];
		String originState = res[1];

		res = destination.split(",");
		String destCity = res[0];
		String destState = res[1];

		// If this list is empty, it means that there is no exact match for the
		// origin-destination in the database, so we need to go by the
		// proximity.
		// List<SearchLoadResult> exactMatchList =
		// findPotentialMatchesBasedOnOriginAndDestiation(originCity,
		// originState,
		// originZip, destCity, destState, destZip);

		// This is a proximity list, under assumption that there is no exact
		// match and we need to fetch data from DistanceMatrix
		List<SearchLoadResult> proximityList = generateProximityList(originCity, originState, destCity, destState);

		List<SearchLoadResult> resultList = rankingService.rank(proximityList, fleetSize, fleetAge);

		
		return resultList;

	}

	private List<SearchLoadResult> generateProximityList(String originCity, String originState, String destCity,
			String destState) {

		// get the entries from DB without any restrictions
		List<SearchLoadResult> listOfAvailableTrucks = searchRepository.findAllInfoNoOriginDestinationRestrictions();

		// user source and destination will be used as our destinations,for
		// example if user enters pleasanton CA and greenwich CT, we will use it
		// as destinations for our matrix
		// {"Pleasanton, CA", "Greenwich, CT" };
		String[] destinations = new String[] { originCity + "," + originState, destCity + "," + destState };

		// Google API has 2500 per day limit, but allows 25by25 per request, so
		// we can chunk it or face the heat and be blocked
		// the way we will run it, we will provide 24 (12 trucks + 12 HQ
		// locations) as origins and 2 destinations (as user entered them)
		// for example:
		

		for (int i = 0; i < listOfAvailableTrucks.size() / LIST_CHUNK; i++) {
			List<SearchLoadResult> list = listOfAvailableTrucks.subList(i * LIST_CHUNK, i * LIST_CHUNK + 12);
			log.info("List size is {} with indexes {}", list.size(),
					i * LIST_CHUNK + "-> " + new Integer(i * LIST_CHUNK + 12));
			list = distanceService.calculateDistanceMatrixWithResults(list, destinations);
		}

		List<SearchLoadResult> returnList = null;
		
		if (listOfAvailableTrucks.size() % LIST_CHUNK != 0) {
			int startIndex = listOfAvailableTrucks.size() - listOfAvailableTrucks.size() % LIST_CHUNK;
			int endIndex = listOfAvailableTrucks.size() - 1;
			returnList = listOfAvailableTrucks.subList(startIndex, endIndex);
			log.info("List size is {} with indexes {}", returnList.size(), startIndex + "-> " + endIndex);
			returnList = distanceService.calculateDistanceMatrixWithResults(returnList, destinations);
		}

		return returnList;
	}
}
