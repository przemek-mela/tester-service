package com.tester.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tester.service.TesterService;

@CrossOrigin(origins = "http://localhost:4220")
@RestController
@RequestMapping("/tester")
public class TesterController {

    @Autowired
    TesterService testerService;
    
    @GetMapping("/countries")
    public Set<String> getTesterCountries(){
	return testerService.testersCountries();
    }
    
}
