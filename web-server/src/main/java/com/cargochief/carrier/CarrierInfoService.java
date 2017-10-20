package com.cargochief.carrier;

public interface CarrierInfoService {

	Iterable<CarrierInfo> findAllCarriers();
	CarrierInfo findOne(Long id);
}
