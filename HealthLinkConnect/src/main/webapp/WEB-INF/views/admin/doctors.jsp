<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Doctor" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Doctors</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <nav class="navbar">
        <h1>🏥 Healthcare System - Admin</h1>
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/admin/dashboard">Dashboard</a>
            <a href="<%= request.getContextPath() %>/admin/doctors">Doctors</a>
            <a href="<%= request.getContextPath() %>/admin/patients">Patients</a>
            <a href="<%= request.getContextPath() %>/admin/appointments">Appointments</a>
            <a href="<%= request.getContextPath() %>/admin/feedback">Feedback</a>
            <a href="<%= request.getContextPath() %>/admin/reports">Reports</a>
            <a href="<%= request.getContextPath() %>/logout">Logout</a>
        </div>
    </nav>
    
    <div class="container">
        <h2>Manage Doctors</h2>
        <a href="<%= request.getContextPath() %>/admin/addDoctor" class="btn-primary">Add New Doctor</a>
        
        <% List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors"); %>
        <% if (doctors != null && !doctors.isEmpty()) { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Specialization</th>
                        <th>Qualification</th>
                        <th>Experience</th>
                        <th>Fee</th>
                        <th>Phone</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Doctor doctor : doctors) { %>
                        <tr>
                            <td><%= doctor.getId() %></td>
                            <td><%= doctor.getFullName() %></td>
                            <td><%= doctor.getSpecialization() %></td>
                            <td><%= doctor.getQualification() %></td>
                            <td><%= doctor.getExperienceYears() %> yrs</td>
                            <td>$<%= doctor.getConsultationFee() %></td>
                            <td><%= doctor.getPhone() %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/admin/deleteDoctor?id=<%= doctor.getId() %>" class="btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No doctors found.</p>
        <% } %>
    </div>
</body>
</html>
