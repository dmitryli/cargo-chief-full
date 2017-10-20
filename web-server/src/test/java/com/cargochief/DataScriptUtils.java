package com.cargochief;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataScriptUtils {

	private final static Logger log = LoggerFactory.getLogger(DataScriptUtils.class);

	@Autowired
	public JdbcTemplate template = null;

	@Value("classpath:CC-DDL")
	Resource ddlStatements = null;

//	@Value("classpath:demo-sql-statements")
//	Resource demoSqlStatements = null;

	public void rebuildDemoDatabase() {
		log.info("###### Drop/Create DB Start ######");
		recreateDatabase();
		log.info("###### Drop/Create DB End ######");

		log.info("###### Create Tables Start ######");
		createTables();
		log.info("###### Create Tables End ######");

//		log.info("###### Populate Tables Start ######");
//		populateTablesWithData();
//		log.info("###### Populate Tables End ######");
	}

//	public void populateTablesWithData() {
//		List<String> sqlQueries = getQueriesFromResource(demoSqlStatements);
//		sqlQueries.forEach(q -> runQuery(q));
//	}

	public void createTables() {

		List<String> ddlQueries = getQueriesFromResource(ddlStatements);
		ddlQueries.forEach(q -> runQuery(q));
	}

	private List<String> getQueriesFromResource(Resource r) {
		List<String> list = new ArrayList<String>();
		try {
			File f = r.getFile();
			assert f != null;
			String allDDL = FileUtils.readFileToString(f);

			String[] ddls = allDDL.split(";");

			for (String s : ddls) {
				if (s != null && s.trim() != "" && !s.contains("#")) {
					list.add(s);
				}
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		}

		return list;
	}

	public void recreateDatabase() {
		runQuery("DROP DATABASE cargochief");

		runQuery("CREATE DATABASE cargochief");

		runQuery("USE cargochief");
	}

	private void runQuery(String query) {
		if (query != null && query.trim() != "") {
			try {
				log.info("Executing {}", query);
				template.execute(query);

			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

	}
}
