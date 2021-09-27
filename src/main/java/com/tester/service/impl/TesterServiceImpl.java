package com.tester.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tester.common.CSVHeplper;
import com.tester.common.StaticDict;
import com.tester.models.Tester;
import com.tester.repository.TesterDao;
import com.tester.service.TesterService;

@Service(value = "testerService")
public class TesterServiceImpl implements TesterService {

    @Autowired
    TesterDao testerDao;

    /**
     * Property from application.properties that contains path for folder containing csv files
     */
    @Value("${app.csv.folder}")
    private String app_csv_folder;
    /**
     * Property from application.properties that contains file path in csv folder for csv file conatining startup data 
     */
    @Value("${app.csv.tester}")
    private String app_csv_tester;

    public void aquireDataFromCsv() {
	List<Tester> testers = csvToList(app_csv_folder + app_csv_tester);
	testerDao.saveAll(testers);
    };

    /**
     * Method reads csv file, maps it to Tester object and converts it to List
     * @param filePath String csv file path
     * @return List<Tester> list of mapped objects from csv file
     */
    private static List<Tester> csvToList(String filePath) {
	List<Tester> testersList = new ArrayList<>();

	Iterable<CSVRecord> csvRecords = CSVHeplper.readCSVFile(filePath);

	for (CSVRecord csvRecord : csvRecords) {
	    Tester tester = new Tester(
		    Integer.parseInt(csvRecord.get(StaticDict.TESTER_HEADERs[0])),
		    csvRecord.get(StaticDict.TESTER_HEADERs[1]),
		    csvRecord.get(StaticDict.TESTER_HEADERs[2]),
		    csvRecord.get(StaticDict.TESTER_HEADERs[3]),
		    Timestamp.valueOf(csvRecord.get(StaticDict.TESTER_HEADERs[4])));

	    testersList.add(tester);
	}

	return testersList;
    }

    public Set<String> testersCountries() {
	Set<String> countries = new HashSet<String>();
	List<Tester> testers = testerDao.findAll();
	testers.stream().forEach(tester -> countries.add(tester.getCountry()));
	return countries;
    }
}
