package com.tester;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tester.service.BugService;
import com.tester.service.DeviceService;
import com.tester.service.TesterDeviceService;
import com.tester.service.TesterService;

@SpringBootApplication
public class TesterServiceApplication {
    
    @Autowired
    BugService bugService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    TesterService testerService;
    @Autowired
    TesterDeviceService testerDeviceService;

    public static void main(String[] args) {
	SpringApplication.run(TesterServiceApplication.class, args);
    }

    @PostConstruct
    public void init() {
	bugService.aquireDataFromCsv();
	deviceService.aquireDataFromCsv();
	testerService.aquireDataFromCsv();
	testerDeviceService.aquireDataFromCsv();
    }
}
