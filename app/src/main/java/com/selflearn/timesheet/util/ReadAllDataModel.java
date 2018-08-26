package com.selflearn.timesheet.util;

/**
 * Created by rkarthic on 14/03/18.
 */

public class ReadAllDataModel {

    private String jobNumber;
    private String dateTime;
    private String client;
    private String jobType;
    private String site_id_lr_code;
    private String siteName;
    private String travelToSite;
    private String travelFromSite;
    private String odometerReading;
    private String kilometers;
    private String startTime;
    private String endTime;
    private String breakTime;
    private String hoursOnSite;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSite_id_lr_code() {
        return site_id_lr_code;
    }

    public void setSite_id_lr_code(String site_id_lr_code) {
        this.site_id_lr_code = site_id_lr_code;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getTravelToSite() {
        return travelToSite;
    }

    public void setTravelToSite(String travelToSite) {
        this.travelToSite = travelToSite;
    }

    public String getTravelFromSite() {
        return travelFromSite;
    }

    public void setTravelFromSite(String travelFromSite) {
        this.travelFromSite = travelFromSite;
    }

    public String getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(String odometerReading) {
        this.odometerReading = odometerReading;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public String getHoursOnSite() {
        return hoursOnSite;
    }

    public void setHoursOnSite(String hoursOnSite) {
        this.hoursOnSite = hoursOnSite;
    }

}
