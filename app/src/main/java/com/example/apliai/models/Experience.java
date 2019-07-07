package com.example.apliai.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "experience_table")
public class Experience {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "experienceId")
    private String eid;
    @ColumnInfo(name = "companyName")
    private String companyName;
    @ColumnInfo(name = "industry")
    private String industry;
    @ColumnInfo(name = "designation")
    private String designation;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "from")
    private String fromDate;
    @ColumnInfo(name = "to")
    private String toDate;

    public Experience() {
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
