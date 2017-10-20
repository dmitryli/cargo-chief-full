package com.cargochief.carrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cargochief.repository.CarrierInfoRepository;

@Service
public class CarrierInfoServiceImpl implements CarrierInfoService {

	private final static Logger log = LoggerFactory.getLogger(CarrierInfoServiceImpl.class);

	@Autowired
	private CarrierInfoRepository carrierInfoRepository = null;



	@Override
	public Iterable<CarrierInfo> findAllCarriers() {
		Iterable<CarrierInfo> all = carrierInfoRepository.findAll();
		return all;
	}


	@Override
	public CarrierInfo findOne(Long id) {
		CarrierInfo l = carrierInfoRepository.findOne(id);
		if (l == null) {
			log.info("No carrier info with id {} is found", id);
		}

		return l;
	}

}
