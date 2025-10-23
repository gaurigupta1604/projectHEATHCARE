<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Patient" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
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
        <h2>My Profile</h2>
        
        <% Patient patient = (Patient) request.getAttribute("patient"); %>
        <% if (patient != null) { %>
            <div class="profile-card">
                <h3>Personal Information</h3>
                <p><strong>Full Name:</strong> <%= patient.getFullName() %></p>
                <p><strong>Phone:</strong> <%= patient.getPhone() != null ? patient.getPhone() : "N/A" %></p>
                <p><strong>Email:</strong> <%= ((com.healthcare.model.User)session.getAttribute("user")).getEmail() %></p>
                <p><strong>Gender:</strong> <%= patient.getGender() != null ? patient.getGender() : "N/A" %></p>
                <p><strong>Date of Birth:</strong> <%= patient.getDateOfBirth() != null ? patient.getDateOfBirth() : "N/A" %></p>
                <p><strong>Blood Group:</strong> <%= patient.getBloodGroup() != null ? patient.getBloodGroup() : "N/A" %></p>
                <p><strong>Address:</strong> <%= patient.getAddress() != null ? patient.getAddress() : "N/A" %></p>
            </div>
        <% } %>
    </div>
</body>
</html>
