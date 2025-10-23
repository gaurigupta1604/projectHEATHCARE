package com.healthcare.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Report {
    private int id;
    private int patientId;
    private int doctorId;
    private String reportType;
    private String reportDetails;
    private Date reportDate;
    private Timestamp createdAt;

    public Report() {}

    public Report(int id, int patientId, int doctorId, String reportType,
                  String reportDetails, Date reportDate, Timestamp createdAt) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reportType = reportType;
        this.reportDetails = reportDetails;
        this.reportDate = reportDate;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
