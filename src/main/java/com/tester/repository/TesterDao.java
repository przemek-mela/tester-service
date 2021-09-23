package com.tester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tester.models.Tester;

@Repository
public interface TesterDao extends JpaRepository<Tester, Integer>{

}
