package com.smistrydev.isitdark.rest;

/**
 * Created by sanjaymistry on 17/10/2016.
 */

public class SunRestCallResult {

    private SunResult results;
    private  String status;

    public SunRestCallResult() {
    }

    public SunResult getResults() {
        return results;
    }

    public void setResults(SunResult results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
