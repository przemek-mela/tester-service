package com.tester.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CSVHeplper {

    public static Iterable<CSVRecord> readCSVFile(String filePath){
	try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
		CSVParser csvParser = new CSVParser(fileReader,
			CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	    Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	    return csvRecords;
	} catch (IOException e) {
	    throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	}
    }
}
