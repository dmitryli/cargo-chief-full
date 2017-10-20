package com.cargochief.capacity;

public interface CapacityInfoService {

	Iterable<CapacityInfo> findAllCapacities();
	CapacityInfo findOne(Long id);
}
