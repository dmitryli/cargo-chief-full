package com.cargochief.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cargochief.search.SearchLoadResult;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

	private final static Logger log = LoggerFactory.getLogger(SearchRepositoryImpl.class);

	private final String QUERY_WITH_ORIGIN_DESTINATION = "SELECT CRI.BUSINESS_NAME, CRI.FIRM_MC, CRI.HQ_CITY, CRI.HQ_STATE, CRI.FLEET_SIZE, CRI.FLEET_AGE, CPI.MINUTE_AGE,CPI.ORIGIN_CITY, CPI.ORIGIN_STATE, CPI.DEST_CITY, CPI.DEST_STATE "
			+ "from CC_CARRIER_INFO CRI INNER JOIN CC_CAPACITY_INFO CPI ON CPI.BUSINESS_NAME = CRI.BUSINESS_NAME "
			+ "WHERE CPI.ORIGIN_CITY = ? AND CPI.ORIGIN_STATE =? AND CPI.ORIGIN_ZIP=? AND CPI.DEST_CITY=? AND CPI.DEST_STATE=? AND CPI.DEST_ZIP = ?";
	
	private final String QUERY_NO_RESTRICTIONS = "SELECT CRI.BUSINESS_NAME, CRI.FIRM_MC, CRI.HQ_CITY, CRI.HQ_STATE, CRI.FLEET_SIZE, CRI.FLEET_AGE, CPI.MINUTE_AGE,CPI.ORIGIN_CITY, CPI.ORIGIN_STATE, CPI.DEST_CITY, CPI.DEST_STATE "
			+ "from CC_CARRIER_INFO CRI INNER JOIN CC_CAPACITY_INFO CPI ON CPI.BUSINESS_NAME = CRI.BUSINESS_NAME ";
	
	
	@Autowired
	JdbcTemplate template = null;

	@Override
	public List<SearchLoadResult> findPotentialMatchesBasedOnOriginAndDestiation(String originCity, String originState,
			String originZip, String destCity, String destState, String destZip) {
		Object[] params = new Object[] { originCity, originState, originZip, destCity, destState, destZip };

		List<SearchLoadResult> list = template.query(QUERY_WITH_ORIGIN_DESTINATION, params, new RowMapper<SearchLoadResult>() {

			@Override
			public SearchLoadResult mapRow(ResultSet rs, int rownumber) throws SQLException {

				SearchLoadResult re = new SearchLoadResult();

				re.setCarrierName(rs.getString("BUSINESS_NAME"));
				re.setCarrierMc(rs.getString("FIRM_MC"));
				re.setHqCity(rs.getString("HQ_CITY"));
				re.setHqState(rs.getString("HQ_STATE"));
				re.setOriginCity(rs.getString("ORIGIN_CITY"));
				re.setOriginState(rs.getString("ORIGIN_STATE"));
				re.setDestCity(rs.getString("DEST_CITY"));
				re.setDestState(rs.getString("DEST_STATE"));
				re.setFleetSize(rs.getInt("FLEET_SIZE"));
				re.setFleetAge(rs.getInt("FLEET_AGE"));
				re.setListingAge(rs.getLong("MINUTE_AGE") / 60);

				return re;

			}
		});

		return list;
	}

	@Override
	public List<SearchLoadResult> findAllInfoNoOriginDestinationRestrictions() {
		List<SearchLoadResult> list = template.query(QUERY_NO_RESTRICTIONS, new RowMapper<SearchLoadResult>() {

			@Override
			public SearchLoadResult mapRow(ResultSet rs, int rownumber) throws SQLException {

				SearchLoadResult re = new SearchLoadResult();

				re.setCarrierName(rs.getString("BUSINESS_NAME"));
				re.setCarrierMc(rs.getString("FIRM_MC"));
				re.setHqCity(rs.getString("HQ_CITY"));
				re.setHqState(rs.getString("HQ_STATE"));
				re.setOriginCity(rs.getString("ORIGIN_CITY"));
				re.setOriginState(rs.getString("ORIGIN_STATE"));
				re.setDestCity(rs.getString("DEST_CITY"));
				re.setDestState(rs.getString("DEST_STATE"));
				re.setFleetSize(rs.getInt("FLEET_SIZE"));
				re.setFleetAge(rs.getInt("FLEET_AGE"));
				re.setListingAge(rs.getLong("MINUTE_AGE") / 60);

				return re;

			}
		});

		return list;
	}

}
