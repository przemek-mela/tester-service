package com.tester.pojo;

/**
 * Interface to obtain getBugsSearchResults Query results and pass it to frontend
 * @author Przemek Mela
 *
 */
public interface SearchResult {

    public String getTesterName();

    public Integer getBugsCount();
}
