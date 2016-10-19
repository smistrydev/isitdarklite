package com.smistrydev.isitdark.rest;

import java.util.Date;

/**
 * Created by sanjaymistry on 17/10/2016.
 */

public class SunResult {

    private Date sunrise;
    private Date sunset;
    private Date solar_noon;
    private Date day_length;
    private Date civil_twilight_begin;
    private Date civil_twilight_end;
    private Date nautical_twilight_begin;
    private Date nautical_twilight_end;
    private Date astronomical_twilight_begin;
    private Date astronomical_twilight_end;

    public SunResult() {
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public Date getSolar_noon() {
        return solar_noon;
    }

    public void setSolar_noon(Date solar_noon) {
        this.solar_noon = solar_noon;
    }

    public Date getDay_length() {
        return day_length;
    }

    public void setDay_length(Date day_length) {
        this.day_length = day_length;
    }

    public Date getCivil_twilight_begin() {
        return civil_twilight_begin;
    }

    public void setCivil_twilight_begin(Date civil_twilight_begin) {
        this.civil_twilight_begin = civil_twilight_begin;
    }

    public Date getCivil_twilight_end() {
        return civil_twilight_end;
    }

    public void setCivil_twilight_end(Date civil_twilight_end) {
        this.civil_twilight_end = civil_twilight_end;
    }

    public Date getNautical_twilight_begin() {
        return nautical_twilight_begin;
    }

    public void setNautical_twilight_begin(Date nautical_twilight_begin) {
        this.nautical_twilight_begin = nautical_twilight_begin;
    }

    public Date getNautical_twilight_end() {
        return nautical_twilight_end;
    }

    public void setNautical_twilight_end(Date nautical_twilight_end) {
        this.nautical_twilight_end = nautical_twilight_end;
    }

    public Date getAstronomical_twilight_begin() {
        return astronomical_twilight_begin;
    }

    public void setAstronomical_twilight_begin(Date astronomical_twilight_begin) {
        this.astronomical_twilight_begin = astronomical_twilight_begin;
    }

    public Date getAstronomical_twilight_end() {
        return astronomical_twilight_end;
    }

    public void setAstronomical_twilight_end(Date astronomical_twilight_end) {
        this.astronomical_twilight_end = astronomical_twilight_end;
    }
}
