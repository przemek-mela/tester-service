package com.tester.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tester.models.Bug;
import com.tester.pojo.SearchResult;

@Repository
public interface BugDao extends JpaRepository<Bug, Integer>{

    @Query("SELECT CONCAT(t.firstName, ' ', t.lastName) AS testerName,\r\n"
    	+ "	   count(b.bugId) AS bugsCount\r\n"
    	+ "FROM Bug AS b\r\n"
    	+ "JOIN Device AS d ON d.deviceId = b.deviceId\r\n"
    	+ "JOIN TesterDevice AS td ON td.deviceId = b.deviceId\r\n"
    	+ "JOIN Tester AS t ON t.testerId = td.testerId\r\n"
    	+ "WHERE ((:devices) IS NULL OR d.deviceId IN (:devices)) \r\n"
    	+ " AND ((:countries) IS NULL OR t.country IN (:countries))\r\n"
    	+ "GROUP BY t.firstName, t.lastName \r\n"
    	+ "ORDER BY count(b.bugId) DESC")
    List<SearchResult> getBugsSearchResults(Optional<List<String>> countries, Optional<List<Integer>> devices);
}
