package com.tester.common;

import org.springframework.stereotype.Component;

@Component
public class StaticDict {

    public static final String CSV_FILE_TYPE = "text/csv";
    public static final String[] BUG_HEADERs = { "bugId", "deviceId", "testerId" };
    public static final String[] DEVICE_HEADERs = { "deviceId", "description" };
    public static final String[] TESTER_HEADERs = { "testerId", "firstName", "lastName", "country", "lastLogin" };
    public static final String[] TESTER_DEVICE_HEADERs = { "testerId", "deviceId" };
}
