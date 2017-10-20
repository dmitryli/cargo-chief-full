package com.cargochief;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.cargochief.ranking.RankingService;
import com.cargochief.ranking.RankingServiceImpl;
import com.cargochief.search.SearchLoadResult;

public class CompareSearchResults {

	
	@Test
	public void testRank2() {

		RankingService r = new RankingServiceImpl();

		SearchLoadResult slr1 = new SearchLoadResult();
		slr1.setCarrierName("A");
		slr1.setDhoMiles(391);
		slr1.setFleetSize(773);
		slr1.setFleetAge(50);
		slr1.setDestinationToHqDistanceMiles(2188);
		
		SearchLoadResult slr2 = new SearchLoadResult();
		slr2.setCarrierName("B");
		slr2.setDhoMiles(34.7);
		slr2.setFleetSize(175);
		slr2.setFleetAge(19);
		slr2.setDestinationToHqDistanceMiles(484);
		
		SearchLoadResult slr3 = new SearchLoadResult();
		slr3.setCarrierName("C");
		slr3.setDhoMiles(119);
		slr3.setFleetSize(901);
		slr3.setFleetAge(22);
		slr3.setDestinationToHqDistanceMiles(1440);
		
		
		List<SearchLoadResult> list = Arrays.asList(new SearchLoadResult[] { slr1, slr2, slr3});
		List<SearchLoadResult> dho = r.rank(list, 10, 15);
		
		for (SearchLoadResult slr : dho) {
			System.out.println(slr.getCarrierName() + " " + slr.getRank());
		}

	}

}
