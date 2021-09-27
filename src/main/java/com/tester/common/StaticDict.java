package com.tester.common;

import org.springframework.stereotype.Component;

@Component
public class StaticDict {

    // Heders to read csv files
    public static final String[] BUG_HEADERs = { "bugId", "deviceId", "testerId" };
    public static final String[] DEVICE_HEADERs = { "deviceId", "description" };
    public static final String[] TESTER_HEADERs = { "testerId", "firstName", "lastName", "country", "lastLogin" };
    public static final String[] TESTER_DEVICE_HEADERs = { "testerId", "deviceId" };
    
}
