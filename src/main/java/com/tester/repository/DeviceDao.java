package com.tester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tester.models.Device;

@Repository
public interface DeviceDao extends JpaRepository<Device, Integer>{

}
