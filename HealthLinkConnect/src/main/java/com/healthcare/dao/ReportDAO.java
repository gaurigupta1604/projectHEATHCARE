package com.healthcare.dao;

import com.healthcare.model.Report;
import com.healthcare.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public void insertReport(Report report) throws SQLException {
        String sql = "INSERT INTO reports (patient_id, doctor_id, report_type, report_details, report_date) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, report.getPatientId());
            stmt.setInt(2, report.getDoctorId());
            stmt.setString(3, report.getReportType());
            stmt.setString(4, report.getReportDetails());
            stmt.setDate(5, report.getReportDate());
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                report.setId(keys.getInt(1));
            }
        }
    }

    public Report selectReportById(int id) throws SQLException {
        String sql = "SELECT * FROM reports WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractReportFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Report> selectReportsByPatientId(int patientId) throws SQLException {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM reports WHERE patient_id = ? ORDER BY report_date DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reports.add(extractReportFromResultSet(rs));
            }
        }
        return reports;
    }

    public List<Report> selectReportsByDoctorId(int doctorId) throws SQLException {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM reports WHERE doctor_id = ? ORDER BY report_date DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reports.add(extractReportFromResultSet(rs));
            }
        }
        return reports;
    }

    public List<Report> selectAllReports() throws SQLException {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM reports ORDER BY report_date DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reports.add(extractReportFromResultSet(rs));
            }
        }
        return reports;
    }

    public boolean updateReport(Report report) throws SQLException {
        String sql = "UPDATE reports SET patient_id = ?, doctor_id = ?, report_type = ?, " +
                     "report_details = ?, report_date = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, report.getPatientId());
            stmt.setInt(2, report.getDoctorId());
            stmt.setString(3, report.getReportType());
            stmt.setString(4, report.getReportDetails());
            stmt.setDate(5, report.getReportDate());
            stmt.setInt(6, report.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteReport(int id) throws SQLException {
        String sql = "DELETE FROM reports WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Report extractReportFromResultSet(ResultSet rs) throws SQLException {
        return new Report(
            rs.getInt("id"),
            rs.getInt("patient_id"),
            rs.getInt("doctor_id"),
            rs.getString("report_type"),
            rs.getString("report_details"),
            rs.getDate("report_date"),
            rs.getTimestamp("created_at")
        );
    }
}
