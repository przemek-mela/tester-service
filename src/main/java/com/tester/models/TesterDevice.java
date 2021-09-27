package com.tester.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model for TesterDevice entity
 * @author Przemek Mela
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tester_device")
public class TesterDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial", name = "tester_device_id")
    private Integer testerDeviceId;
    @Column(name = "tester_id")
    private Integer testerId;
    @Column(name = "device_id")
    private Integer deviceId;
    
    public TesterDevice(Integer testerId, Integer deviceId) {
	this.testerId = testerId;
	this.deviceId = deviceId;
    }
    
}
