package com.healthcare.dao;

import com.healthcare.model.Patient;
import com.healthcare.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void insertPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (user_id, full_name, phone, address, date_of_birth, gender, blood_group) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, patient.getUserId());
            stmt.setString(2, patient.getFullName());
            stmt.setString(3, patient.getPhone());
            stmt.setString(4, patient.getAddress());
            stmt.setDate(5, patient.getDateOfBirth());
            stmt.setString(6, patient.getGender());
            stmt.setString(7, patient.getBloodGroup());
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                patient.setId(keys.getInt(1));
            }
        }
    }

    public Patient selectPatientById(int id) throws SQLException {
        String sql = "SELECT * FROM patients WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPatientFromResultSet(rs);
            }
        }
        return null;
    }

    public Patient selectPatientByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM patients WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPatientFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Patient> selectAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                patients.add(extractPatientFromResultSet(rs));
            }
        }
        return patients;
    }

    public boolean updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patients SET full_name = ?, phone = ?, address = ?, " +
                     "date_of_birth = ?, gender = ?, blood_group = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getFullName());
            stmt.setString(2, patient.getPhone());
            stmt.setString(3, patient.getAddress());
            stmt.setDate(4, patient.getDateOfBirth());
            stmt.setString(5, patient.getGender());
            stmt.setString(6, patient.getBloodGroup());
            stmt.setInt(7, patient.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletePatient(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Patient extractPatientFromResultSet(ResultSet rs) throws SQLException {
        return new Patient(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getString("full_name"),
            rs.getString("phone"),
            rs.getString("address"),
            rs.getDate("date_of_birth"),
            rs.getString("gender"),
            rs.getString("blood_group")
        );
    }
}
