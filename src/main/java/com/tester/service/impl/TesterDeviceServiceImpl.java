package com.tester.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
    /**
     * Property from application.properties that contains path for folder containing csv files
     */
    @Value("${app.csv.folder}")
    private String app_csv_folder;
    /**
     * Property from application.properties that contains file path in csv folder for csv file conatining startup data 
     */
    @Value("${app.csv.tester-device}")
    private String app_csv_tester_device;
    
    public void aquireDataFromCsv() {
	List<TesterDevice> testerDevices = csvToList(app_csv_folder + app_csv_tester_device);
	testerDeviceDao.saveAll(testerDevices);
    };

    /**
     * Method reads csv file, maps it to TesterDevice object and converts it to List
     * @param filePath String csv file path
     * @return List<TesterDevice> list of mapped objects from csv file
     */
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
