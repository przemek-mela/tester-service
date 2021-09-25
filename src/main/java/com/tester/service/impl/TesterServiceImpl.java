package com.tester.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void aquireDataFromCsv() {
	List<Tester> testers = csvToList("./data/testers.csv");
	testerDao.saveAll(testers);
	System.out.println("Wczytano: " + testers.size());
    };

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

    public Set<String> testersCountries(){
	Set<String> countries = new HashSet<String>();
	List<Tester> testers = testerDao.findAll();
	testers.stream().forEach(tester -> countries.add(tester.getCountry()));
	return countries;
    }
}
