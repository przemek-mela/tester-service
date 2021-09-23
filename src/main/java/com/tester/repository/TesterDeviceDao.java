package com.tester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tester.models.TesterDevice;

@Repository
public interface TesterDeviceDao extends JpaRepository<TesterDevice, Integer>{

}
