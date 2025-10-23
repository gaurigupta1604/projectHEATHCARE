<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Doctor" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Find Doctors</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <nav class="navbar">
        <h1>🏥 Healthcare System - Patient</h1>
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/patient/dashboard">Dashboard</a>
            <a href="<%= request.getContextPath() %>/patient/profile">Profile</a>
            <a href="<%= request.getContextPath() %>/patient/doctors">Find Doctors</a>
            <a href="<%= request.getContextPath() %>/patient/appointments">Appointments</a>
            <a href="<%= request.getContextPath() %>/patient/reports">Reports</a>
            <a href="<%= request.getContextPath() %>/patient/feedback">Feedback</a>
            <a href="<%= request.getContextPath() %>/logout">Logout</a>
        </div>
    </nav>
    
    <div class="container">
        <h2>Available Doctors</h2>
        
        <% List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors"); %>
        
        <% if (doctors != null && !doctors.isEmpty()) { %>
            <div class="doctors-grid">
                <% for (Doctor doctor : doctors) { %>
                    <div class="doctor-card">
                        <div class="doctor-icon">👨‍⚕️</div>
                        <h3><%= doctor.getFullName() %></h3>
                        <p class="specialization"><%= doctor.getSpecialization() %></p>
                        <p><strong>Qualification:</strong> <%= doctor.getQualification() %></p>
                        <p><strong>Experience:</strong> <%= doctor.getExperienceYears() %> years</p>
                        <p><strong>Fee:</strong> $<%= doctor.getConsultationFee() %></p>
                        <p><strong>Phone:</strong> <%= doctor.getPhone() %></p>
                        <a href="<%= request.getContextPath() %>/patient/book?doctorId=<%= doctor.getId() %>" class="btn-primary">Book Appointment</a>
                    </div>
                <% } %>
            </div>
        <% } else { %>
            <p>No doctors available at the moment.</p>
        <% } %>
    </div>
</body>
</html>
