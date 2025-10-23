package com.healthcare.util;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:h2:./healthcare;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static void initializeDatabase() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "username VARCHAR(50) UNIQUE NOT NULL, " +
            "password VARCHAR(255) NOT NULL, " +
            "email VARCHAR(100) UNIQUE NOT NULL, " +
            "role VARCHAR(20) NOT NULL, " +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";

        String createPatientsTable = "CREATE TABLE IF NOT EXISTS patients (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "user_id INT UNIQUE NOT NULL, " +
            "full_name VARCHAR(100) NOT NULL, " +
            "phone VARCHAR(20), " +
            "address VARCHAR(255), " +
            "date_of_birth DATE, " +
            "gender VARCHAR(10), " +
            "blood_group VARCHAR(10), " +
            "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
            ")";

        String createDoctorsTable = "CREATE TABLE IF NOT EXISTS doctors (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "user_id INT UNIQUE NOT NULL, " +
            "full_name VARCHAR(100) NOT NULL, " +
            "specialization VARCHAR(100) NOT NULL, " +
            "phone VARCHAR(20), " +
            "qualification VARCHAR(255), " +
            "experience_years INT, " +
            "consultation_fee DECIMAL(10,2), " +
            "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
            ")";

        String createAppointmentsTable = "CREATE TABLE IF NOT EXISTS appointments (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "patient_id INT NOT NULL, " +
            "doctor_id INT NOT NULL, " +
            "appointment_date DATE NOT NULL, " +
            "appointment_time VARCHAR(10) NOT NULL, " +
            "status VARCHAR(20) DEFAULT 'Scheduled', " +
            "symptoms TEXT, " +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE, " +
            "FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE" +
            ")";

        String createReportsTable = "CREATE TABLE IF NOT EXISTS reports (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "patient_id INT NOT NULL, " +
            "doctor_id INT NOT NULL, " +
            "report_type VARCHAR(100) NOT NULL, " +
            "report_details TEXT, " +
            "report_date DATE NOT NULL, " +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE, " +
            "FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE" +
            ")";

        String createFeedbackTable = "CREATE TABLE IF NOT EXISTS feedback (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "patient_id INT NOT NULL, " +
            "doctor_id INT, " +
            "appointment_id INT, " +
            "rating INT CHECK (rating >= 1 AND rating <= 5), " +
            "comments TEXT, " +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE, " +
            "FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE SET NULL, " +
            "FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE SET NULL" +
            ")";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createPatientsTable);
            stmt.execute(createDoctorsTable);
            stmt.execute(createAppointmentsTable);
            stmt.execute(createReportsTable);
            stmt.execute(createFeedbackTable);
            
            String checkAdminSql = "SELECT COUNT(*) FROM users WHERE username = 'admin'";
            var rs = stmt.executeQuery(checkAdminSql);
            if (rs.next() && rs.getInt(1) == 0) {
                String hashedPassword = BCrypt.hashpw("admin123", BCrypt.gensalt());
                String insertAdminSql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertAdminSql)) {
                    pstmt.setString(1, "admin");
                    pstmt.setString(2, hashedPassword);
                    pstmt.setString(3, "admin@healthcare.com");
                    pstmt.setString(4, "ADMIN");
                    pstmt.executeUpdate();
                }
            }
            
            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
