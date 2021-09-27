package com.tester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tester.models.Device;
import com.tester.repository.DeviceDao;

@CrossOrigin(origins = "http://localhost:4220")
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceDao deviceDao;

    /**
     * Method that returns list of Devices currently available in database
     * @return List<Device> list of all devices
     */
    @GetMapping("")
    public List<Device> getAllDevices() {
	return deviceDao.findAll();
    }
}
