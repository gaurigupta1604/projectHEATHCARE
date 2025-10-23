package com.healthcare.dao;

import com.healthcare.model.Doctor;
import com.healthcare.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void insertDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO doctors (user_id, full_name, specialization, phone, qualification, experience_years, consultation_fee) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, doctor.getUserId());
            stmt.setString(2, doctor.getFullName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setString(4, doctor.getPhone());
            stmt.setString(5, doctor.getQualification());
            stmt.setInt(6, doctor.getExperienceYears());
            stmt.setDouble(7, doctor.getConsultationFee());
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                doctor.setId(keys.getInt(1));
            }
        }
    }

    public Doctor selectDoctorById(int id) throws SQLException {
        String sql = "SELECT * FROM doctors WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractDoctorFromResultSet(rs);
            }
        }
        return null;
    }

    public Doctor selectDoctorByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM doctors WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractDoctorFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Doctor> selectAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors ORDER BY full_name";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                doctors.add(extractDoctorFromResultSet(rs));
            }
        }
        return doctors;
    }

    public List<Doctor> selectDoctorsBySpecialization(String specialization) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors WHERE specialization = ? ORDER BY full_name";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, specialization);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                doctors.add(extractDoctorFromResultSet(rs));
            }
        }
        return doctors;
    }

    public boolean updateDoctor(Doctor doctor) throws SQLException {
        String sql = "UPDATE doctors SET full_name = ?, specialization = ?, phone = ?, " +
                     "qualification = ?, experience_years = ?, consultation_fee = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.getFullName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getPhone());
            stmt.setString(4, doctor.getQualification());
            stmt.setInt(5, doctor.getExperienceYears());
            stmt.setDouble(6, doctor.getConsultationFee());
            stmt.setInt(7, doctor.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteDoctor(int id) throws SQLException {
        String sql = "DELETE FROM doctors WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Doctor extractDoctorFromResultSet(ResultSet rs) throws SQLException {
        return new Doctor(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getString("full_name"),
            rs.getString("specialization"),
            rs.getString("phone"),
            rs.getString("qualification"),
            rs.getInt("experience_years"),
            rs.getDouble("consultation_fee")
        );
    }
}
