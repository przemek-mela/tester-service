package com.tester.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tester.pojo.SearchResult;
import com.tester.repository.BugDao;

@CrossOrigin(origins = "http://localhost:4220")
@RestController
@RequestMapping("/bug")
public class BugController {

    @Autowired
    BugDao bugDao;

    @GetMapping("/search")
    public List<SearchResult> getBugs(@RequestParam("countries") Optional<List<String>> countries, @RequestParam("devices") Optional<List<Integer>> devices) {
	return bugDao.getBugsSearchResults(countries, devices);
    }
}
