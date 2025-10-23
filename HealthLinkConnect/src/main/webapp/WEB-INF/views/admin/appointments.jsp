<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Appointment" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Appointments</title>
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
        <h2>All Appointments</h2>
        
        <% List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments"); %>
        <% if (appointments != null && !appointments.isEmpty()) { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Patient ID</th>
                        <th>Doctor ID</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Symptoms</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Appointment apt : appointments) { %>
                        <tr>
                            <td><%= apt.getId() %></td>
                            <td><%= apt.getPatientId() %></td>
                            <td><%= apt.getDoctorId() %></td>
                            <td><%= apt.getAppointmentDate() %></td>
                            <td><%= apt.getAppointmentTime() %></td>
                            <td><span class="status <%= apt.getStatus().toLowerCase() %>"><%= apt.getStatus() %></span></td>
                            <td><%= apt.getSymptoms() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No appointments found.</p>
        <% } %>
    </div>
</body>
</html>
