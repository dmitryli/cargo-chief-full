package com.cargochief.repository;

import org.springframework.data.repository.CrudRepository;

import com.cargochief.capacity.CapacityInfo;

/*
 * Dima: This is an interface that will access the database using JPA to fetch info from CC_CAPACITY_INFO
 */

public interface CapacityRepository extends CrudRepository<CapacityInfo, Long> {

}
