package com.tester.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tester.common.CSVHeplper;
import com.tester.common.StaticDict;
import com.tester.models.TesterDevice;
import com.tester.repository.TesterDeviceDao;
import com.tester.service.TesterDeviceService;

@Service(value = "testerDeviceService")
public class TesterDeviceServiceImpl implements TesterDeviceService{

    @Autowired
    TesterDeviceDao testerDeviceDao;
    
    public void aquireDataFromCsv() {
	List<TesterDevice> testerDevices = csvToList("./data/tester_device.csv");
	testerDeviceDao.saveAll(testerDevices);
	System.out.println("Wczytano: " + testerDevices.size());
    };

    private static List<TesterDevice> csvToList(String filePath) {
	List<TesterDevice> testersList = new ArrayList<>();

	Iterable<CSVRecord> csvRecords = CSVHeplper.readCSVFile(filePath);

	for (CSVRecord csvRecord : csvRecords) {
	    TesterDevice testerDevice = new TesterDevice(
		    Integer.parseInt(csvRecord.get(StaticDict.TESTER_DEVICE_HEADERs[0])),
		    Integer.parseInt(csvRecord.get(StaticDict.TESTER_DEVICE_HEADERs[1])));

	    testersList.add(testerDevice);
	}

	return testersList;
    }
}
