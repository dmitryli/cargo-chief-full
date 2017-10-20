package com.cargochief.ranking;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cargochief.search.SearchLoadResult;

/*
 * Dima: Ranking criteria (1 to 9) based on the criteria below, only 40 top
 * results are needed The rank of these listings is influenced by 50% DH-O
 * being low, and 20% how close to size of fleet, and 15% how close to age
 * of fleet, and 15% how close the Load Destination is to the Carrier HQ
 * Location
 */
@Service
public class RankingServiceImpl implements RankingService {
	private final static int CAP_RESULTS = 40;

	/* (non-Javadoc)
	 * @see com.cargochief.service.RankingService#rank(java.util.List, long, long)
	 */
	@Override
	public List<SearchLoadResult> rank(List<SearchLoadResult> list, long fleetSize, long fleetAge) {
		for (SearchLoadResult slr : list) {
			double rank = 0.5 * slr.getDhoMiles() + 0.2 * Math.abs(slr.getFleetSize() - fleetSize)
					+ 0.15 * Math.abs(slr.getFleetAge() - fleetAge) + 0.15 * slr.getDestinationToHqDistanceMiles();

			slr.setRank(rank);
		}

		list.sort(new Comparator<SearchLoadResult>() {

			@Override
			public int compare(SearchLoadResult o1, SearchLoadResult o2) {
				int result = 0;
				if (o1.getRank() > o2.getRank()) {
					result = 1;
				} else if (o1.getRank() == o2.getRank()) {
					result = 0;
				} else {
					result = -1;
				}
				return result;
			}
		});
		if (list.size() > CAP_RESULTS) {
			list = list.subList(0, CAP_RESULTS);
		}

		return list;
	}

}
