package com.cargochief.capacity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cargochief.repository.CapacityRepository;

@Service
public class CapacityInfoServiceImpl implements CapacityInfoService {

	private final static Logger log = LoggerFactory.getLogger(CapacityInfoServiceImpl.class);

	@Autowired
	private CapacityRepository capacityRepository = null;

	@Override
	public Iterable<CapacityInfo> findAllCapacities() {
		Iterable<CapacityInfo> all = capacityRepository.findAll();
		return all;
	}

	@Override
	public CapacityInfo findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
