package com.cargochief.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cargochief.capacity.CapacityInfo;
import com.cargochief.capacity.CapacityInfoService;

/*
 * Dima: fetch data all the way from CC_CARRIER_INFO table
 */
@RestController
public class CapacityController {

	@Autowired
	private CapacityInfoService capacityInfoService = null;

	@RequestMapping(path = "/capacity-info", method = RequestMethod.GET, produces = "application/json")
	public Iterable<CapacityInfo> showAllCapacity() {
		return capacityInfoService.findAllCapacities();
	}
}
