<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Doctor" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <nav class="navbar">
        <h1>🏥 Healthcare System - Doctor</h1>
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/doctor/dashboard">Dashboard</a>
            <a href="<%= request.getContextPath() %>/doctor/profile">Profile</a>
            <a href="<%= request.getContextPath() %>/doctor/appointments">Appointments</a>
            <a href="<%= request.getContextPath() %>/doctor/patients">Patients</a>
            <a href="<%= request.getContextPath() %>/logout">Logout</a>
        </div>
    </nav>
    
    <div class="container">
        <h2>My Profile</h2>
        
        <% Doctor doctor = (Doctor) request.getAttribute("doctor"); %>
        <% if (doctor != null) { %>
            <div class="profile-card">
                <h3>Professional Information</h3>
                <p><strong>Name:</strong> Dr. <%= doctor.getFullName() %></p>
                <p><strong>Specialization:</strong> <%= doctor.getSpecialization() %></p>
                <p><strong>Qualification:</strong> <%= doctor.getQualification() %></p>
                <p><strong>Experience:</strong> <%= doctor.getExperienceYears() %> years</p>
                <p><strong>Consultation Fee:</strong> $<%= doctor.getConsultationFee() %></p>
                <p><strong>Phone:</strong> <%= doctor.getPhone() %></p>
            </div>
        <% } %>
    </div>
</body>
</html>
