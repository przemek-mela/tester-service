package com.tester.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tester.common.CSVHeplper;
import com.tester.common.StaticDict;
import com.tester.models.Device;
import com.tester.repository.DeviceDao;
import com.tester.service.DeviceService;

@Service(value = "deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceDao deviceDao;

    /**
     * Property from application.properties that contains path for folder containing csv files
     */
    @Value("${app.csv.folder}")
    private String app_csv_folder;
    /**
     * Property from application.properties that contains file path in csv folder for csv file conatining startup data 
     */
    @Value("${app.csv.device}")
    private String app_csv_device;

    public void aquireDataFromCsv() {
	List<Device> devices = csvToList(app_csv_folder + app_csv_device);
	deviceDao.saveAll(devices);
    };

    /**
     * Method reads csv file, maps it to Device object and converts it to List
     * @param filePath String csv file path
     * @return List<Device> list of mapped objects from csv file
     */
    private static List<Device> csvToList(String filePath) {
	List<Device> devicesList = new ArrayList<>();

	Iterable<CSVRecord> csvRecords = CSVHeplper.readCSVFile(filePath);

	for (CSVRecord csvRecord : csvRecords) {
	    Device device = new Device(
		    Integer.parseInt(csvRecord.get(StaticDict.DEVICE_HEADERs[0])),
		    csvRecord.get(StaticDict.DEVICE_HEADERs[1]));

	    devicesList.add(device);
	}

	return devicesList;
    }

}
