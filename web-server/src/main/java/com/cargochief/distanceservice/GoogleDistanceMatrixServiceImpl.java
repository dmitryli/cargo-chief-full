package com.cargochief.distanceservice;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cargochief.search.SearchLoadResult;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;

@Service
public class GoogleDistanceMatrixServiceImpl implements DistanceService {

	private final Integer ORIGIN_CHUNK = 24;
	private final static String MY_API_KEY = "AIzaSyDAcxnqLtvtEYFHm2fbJNq0PHM6ZxyXwNU";
	private final GeoApiContext context = new GeoApiContext.Builder().apiKey(MY_API_KEY).build();

	private final static Logger log = LoggerFactory.getLogger(GoogleDistanceMatrixServiceImpl.class);

	// assumption that the list already chunked to size of 12 trucks.
	@Override
	public List<SearchLoadResult> calculateDistanceMatrixWithResults(List<SearchLoadResult> list,
			String[] destinations) {

		log.info("User going from: {} to {}", destinations[0], destinations[1]);

		String[] origins = new String[ORIGIN_CHUNK];

		for (int i = 0; i < Math.min(list.size(), ORIGIN_CHUNK/2);  i++) {
			origins[i*2] = list.get(i).getFullOrigin();
			origins[i*2 + 1] = list.get(i).getFullHq();

		}

		DistanceMatrixApiRequest dm = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations)
				.units(Unit.IMPERIAL);
		try {
			DistanceMatrix distanceMatrix = dm.await();
			for (int i = 0; i < Math.min(list.size(), ORIGIN_CHUNK/2); i++) {
				SearchLoadResult slr = list.get(i);

				slr.setDho(distanceMatrix.rows[i*2].elements[0].distance.humanReadable);
				slr.setDhoMiles(distanceMatrix.rows[i*2].elements[0].distance.inMeters);
				
				slr.setDestinationToHqDistance(distanceMatrix.rows[i*2+1].elements[1].distance.humanReadable);
				slr.setDestinationToHqDistanceMiles(distanceMatrix.rows[i*2+1].elements[1].distance.inMeters / 1609.344);
				log.info("Truck " + slr.getCarrierName() + " from " + slr.getFullOrigin() + " to " + slr.getFullDestination() + " with HQ in " + slr.getFullHq());
				log.info("DHO is: {} and distance from destination to HQ is {}", slr.getDho(), slr.getDestinationToHqDistance());
			}
		} catch (ApiException | InterruptedException | IOException e) {
			log.error(e.getMessage());
		}

		return list;
	}
}
