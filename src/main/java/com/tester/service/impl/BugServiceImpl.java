package com.tester.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
    /**
     * Property from application.properties that contains path for folder containing csv files
     */
    @Value("${app.csv.folder}")
    private String app_csv_folder;
    /**
     * Property from application.properties that contains file path in csv folder for csv file conatining startup data 
     */
    @Value("${app.csv.bug}")
    private String app_csv_bug;

    public void aquireDataFromCsv() {
	List<Bug> bugs = csvToList(app_csv_folder + app_csv_bug);
	bugDao.saveAll(bugs);
    };

    /**
     * Method reads csv file, maps it to Bug object and converts it to List
     * @param filePath String csv file path
     * @return List<Bug> list of mapped objects from csv file
     */
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
