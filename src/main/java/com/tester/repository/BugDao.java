package com.tester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tester.models.Bug;

@Repository
public interface BugDao extends JpaRepository<Bug, Integer>{

}
