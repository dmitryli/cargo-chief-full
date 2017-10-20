package com.cargochief;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.Unit;

public class MapsTest {
	private final static String MY_API_KEY = "AIzaSyDAcxnqLtvtEYFHm2fbJNq0PHM6ZxyXwNU";
	
	@Test
	
	public void testSimpleNavigation() {
		String[] origins = new String[] {"Ontario, CA", "Berkeley ,CA", "Ontario ,CA", "Allentown ,PA"};
		String[] destinations = new String[] {"palo alto,ca" , "menasha,WA"};
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey(MY_API_KEY).build();
		
		DistanceMatrixApiRequest dm = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations).units(Unit.IMPERIAL);
		try {
			DistanceMatrix distanceMatrix = dm.await();
			for (DistanceMatrixRow r :  distanceMatrix.rows) {
				for (DistanceMatrixElement er : r.elements) {
					System.out.println(er.distance + " " + er.duration + " " + er.durationInTraffic);
				}
			}
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	
	@Ignore
	@Test
	public void testOriginDestionationDhoHQMatrix() {
		// Location of the truck and location of HQ
		String[] origins = new String[] {"Livermore CA", "Mount Vernon, NY"};
		
		// user source and destination
		String[] destinations = new String[] {"Pleasanton, CA", "Greenwich, CT" };
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey(MY_API_KEY).build();
		
		DistanceMatrixApiRequest dm = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations).units(Unit.IMPERIAL);
		try {
			DistanceMatrix distanceMatrix = dm.await();
			for (DistanceMatrixRow r :  distanceMatrix.rows) {
				for (DistanceMatrixElement er : r.elements) {
					System.out.println(er.distance + " " + er.duration + " " + er.durationInTraffic);
				}
			}
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
