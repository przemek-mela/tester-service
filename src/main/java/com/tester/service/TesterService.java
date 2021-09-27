package com.tester.service;

import java.util.Set;

public interface TesterService {

    /**
     * Method that aquaiers csv data from data folder and stores it in database
     */
    public void aquireDataFromCsv();

    /**
     * Method that returns all countries that testers come from
     * @return Set<String> list of countries
     */
    public Set<String> testersCountries();
}
