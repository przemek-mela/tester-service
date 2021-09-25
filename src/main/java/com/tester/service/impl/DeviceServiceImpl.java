package com.tester.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tester.common.CSVHeplper;
import com.tester.common.StaticDict;
import com.tester.models.Device;
import com.tester.repository.DeviceDao;
import com.tester.service.DeviceService;

@Service(value = "deviceService")
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    DeviceDao deviceDao;
    
    public void aquireDataFromCsv() {
	List<Device> devices = csvToList("./data/devices.csv");
	deviceDao.saveAll(devices);
	System.out.println("Wczytano: " + devices.size());
    };


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
