package com.sample.news.data.models;

import java.util.List;

/**
 * Model class that contains data of all news items.
 * Parcelable to pass data between fragments
 */
public class NewsResponse {

    private String status;

    private String copyright;

    private String section;

    private String lastUpdated;

    private Integer numResults;

    private List<NewsEntity> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<NewsEntity> getResults() {
        return results;
    }

    public void setResults(List<NewsEntity> results) {
        this.results = results;
    }

}
