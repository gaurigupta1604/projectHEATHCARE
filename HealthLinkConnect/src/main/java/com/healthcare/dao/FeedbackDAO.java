package com.healthcare.dao;

import com.healthcare.model.Feedback;
import com.healthcare.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public void insertFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO feedback (patient_id, doctor_id, appointment_id, rating, comments) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, feedback.getPatientId());
            if (feedback.getDoctorId() != null) {
                stmt.setInt(2, feedback.getDoctorId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            if (feedback.getAppointmentId() != null) {
                stmt.setInt(3, feedback.getAppointmentId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setInt(4, feedback.getRating());
            stmt.setString(5, feedback.getComments());
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                feedback.setId(keys.getInt(1));
            }
        }
    }

    public Feedback selectFeedbackById(int id) throws SQLException {
        String sql = "SELECT * FROM feedback WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractFeedbackFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Feedback> selectFeedbackByPatientId(int patientId) throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM feedback WHERE patient_id = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                feedbacks.add(extractFeedbackFromResultSet(rs));
            }
        }
        return feedbacks;
    }

    public List<Feedback> selectFeedbackByDoctorId(int doctorId) throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM feedback WHERE doctor_id = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                feedbacks.add(extractFeedbackFromResultSet(rs));
            }
        }
        return feedbacks;
    }

    public List<Feedback> selectAllFeedback() throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM feedback ORDER BY created_at DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                feedbacks.add(extractFeedbackFromResultSet(rs));
            }
        }
        return feedbacks;
    }

    public boolean updateFeedback(Feedback feedback) throws SQLException {
        String sql = "UPDATE feedback SET patient_id = ?, doctor_id = ?, appointment_id = ?, " +
                     "rating = ?, comments = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getPatientId());
            if (feedback.getDoctorId() != null) {
                stmt.setInt(2, feedback.getDoctorId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            if (feedback.getAppointmentId() != null) {
                stmt.setInt(3, feedback.getAppointmentId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setInt(4, feedback.getRating());
            stmt.setString(5, feedback.getComments());
            stmt.setInt(6, feedback.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteFeedback(int id) throws SQLException {
        String sql = "DELETE FROM feedback WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Feedback extractFeedbackFromResultSet(ResultSet rs) throws SQLException {
        Integer doctorId = rs.getInt("doctor_id");
        if (rs.wasNull()) doctorId = null;
        
        Integer appointmentId = rs.getInt("appointment_id");
        if (rs.wasNull()) appointmentId = null;
        
        return new Feedback(
            rs.getInt("id"),
            rs.getInt("patient_id"),
            doctorId,
            appointmentId,
            rs.getInt("rating"),
            rs.getString("comments"),
            rs.getTimestamp("created_at")
        );
    }
}
