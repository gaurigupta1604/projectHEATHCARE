<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Appointment" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Appointments</title>
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
        <h2>My Appointments</h2>
        <a href="<%= request.getContextPath() %>/patient/book" class="btn-primary">Book New Appointment</a>
        
        <% List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments"); %>
        
        <% if (appointments != null && !appointments.isEmpty()) { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Symptoms</th>
                        <th>Booked On</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Appointment apt : appointments) { %>
                        <tr>
                            <td><%= apt.getId() %></td>
                            <td><%= apt.getAppointmentDate() %></td>
                            <td><%= apt.getAppointmentTime() %></td>
                            <td><span class="status <%= apt.getStatus().toLowerCase() %>"><%= apt.getStatus() %></span></td>
                            <td><%= apt.getSymptoms() %></td>
                            <td><%= apt.getCreatedAt() %></td>
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
