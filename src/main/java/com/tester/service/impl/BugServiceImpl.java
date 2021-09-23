package com.tester.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tester.common.CSVHeplper;
import com.tester.common.StaticDict;
import com.tester.models.Bug;
import com.tester.repository.BugDao;
import com.tester.service.BugService;

@Service(value = "bugService")
public class BugServiceImpl implements BugService {

    @Autowired
    BugDao bugDao;
    
    public void aquireDataFromCsv() {
	List<Bug> bugs = csvToList("./data/bugs.csv");
	bugDao.saveAll(bugs);
	System.out.println("Wczytano: " + bugs.size());
    };


    private static List<Bug> csvToList(String filePath) {
	    List<Bug> bugsList = new ArrayList<>();

	    Iterable<CSVRecord> csvRecords = CSVHeplper.readCSVFile(filePath);

	    for (CSVRecord csvRecord : csvRecords) {
		Bug bug = new Bug(
			Integer.parseInt(csvRecord.get(StaticDict.BUG_HEADERs[0])),
			Integer.parseInt(csvRecord.get(StaticDict.BUG_HEADERs[1])),
			Integer.parseInt(csvRecord.get(StaticDict.BUG_HEADERs[2])));

		bugsList.add(bug);
	    }

	    return bugsList;
    }
}
