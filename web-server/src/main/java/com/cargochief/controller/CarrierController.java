package com.cargochief.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cargochief.carrier.CarrierInfo;
import com.cargochief.carrier.CarrierInfoService;
/*
 * Dima: fetch data all the way from CC_CARRIER_INFO table
 */
@RestController
public class CarrierController {

	@Autowired
	private CarrierInfoService carrierInfoService = null;
	
	@RequestMapping(path = "/carrier-info", method = RequestMethod.GET, produces = "application/json")
	public Iterable<CarrierInfo> showAllCarriers() {
		return carrierInfoService.findAllCarriers();
	}
}
