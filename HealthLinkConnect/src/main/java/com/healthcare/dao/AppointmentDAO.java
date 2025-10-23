package com.healthcare.dao;

import com.healthcare.model.Appointment;
import com.healthcare.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public void insertAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status, symptoms) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setDate(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getAppointmentTime());
            stmt.setString(5, appointment.getStatus());
            stmt.setString(6, appointment.getSymptoms());
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                appointment.setId(keys.getInt(1));
            }
        }
    }

    public Appointment selectAppointmentById(int id) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAppointmentFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Appointment> selectAppointmentsByPatientId(int patientId) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE patient_id = ? ORDER BY appointment_date DESC, appointment_time DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(extractAppointmentFromResultSet(rs));
            }
        }
        return appointments;
    }

    public List<Appointment> selectAppointmentsByDoctorId(int doctorId) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE doctor_id = ? ORDER BY appointment_date DESC, appointment_time DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(extractAppointmentFromResultSet(rs));
            }
        }
        return appointments;
    }

    public List<Appointment> selectAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY appointment_date DESC, appointment_time DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                appointments.add(extractAppointmentFromResultSet(rs));
            }
        }
        return appointments;
    }

    public boolean updateAppointmentStatus(int id, String status) throws SQLException {
        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointments SET patient_id = ?, doctor_id = ?, appointment_date = ?, " +
                     "appointment_time = ?, status = ?, symptoms = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setDate(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getAppointmentTime());
            stmt.setString(5, appointment.getStatus());
            stmt.setString(6, appointment.getSymptoms());
            stmt.setInt(7, appointment.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteAppointment(int id) throws SQLException {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Appointment extractAppointmentFromResultSet(ResultSet rs) throws SQLException {
        return new Appointment(
            rs.getInt("id"),
            rs.getInt("patient_id"),
            rs.getInt("doctor_id"),
            rs.getDate("appointment_date"),
            rs.getString("appointment_time"),
            rs.getString("status"),
            rs.getString("symptoms"),
            rs.getTimestamp("created_at")
        );
    }
}
