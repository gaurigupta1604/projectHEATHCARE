<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Patient" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Patients</title>
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
        <h2>My Patients</h2>
        
        <% List<Patient> patients = (List<Patient>) request.getAttribute("patients"); %>
        <% if (patients != null && !patients.isEmpty()) { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Gender</th>
                        <th>Blood Group</th>
                        <th>Date of Birth</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Patient patient : patients) { %>
                        <tr>
                            <td><%= patient.getId() %></td>
                            <td><%= patient.getFullName() %></td>
                            <td><%= patient.getPhone() %></td>
                            <td><%= patient.getGender() %></td>
                            <td><%= patient.getBloodGroup() %></td>
                            <td><%= patient.getDateOfBirth() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No patients found.</p>
        <% } %>
    </div>
</body>
</html>
