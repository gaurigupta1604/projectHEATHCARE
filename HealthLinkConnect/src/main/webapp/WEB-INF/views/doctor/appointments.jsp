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
        <h2>All Appointments</h2>
        
        <% List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments"); %>
        <% if (appointments != null && !appointments.isEmpty()) { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Patient ID</th>
                        <th>Symptoms</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Appointment apt : appointments) { %>
                        <tr>
                            <td><%= apt.getId() %></td>
                            <td><%= apt.getAppointmentDate() %></td>
                            <td><%= apt.getAppointmentTime() %></td>
                            <td><%= apt.getPatientId() %></td>
                            <td><%= apt.getSymptoms() %></td>
                            <td><span class="status <%= apt.getStatus().toLowerCase() %>"><%= apt.getStatus() %></span></td>
                            <td>
                                <% if ("Scheduled".equals(apt.getStatus())) { %>
                                    <form method="post" action="<%= request.getContextPath() %>/doctor/updateStatus" style="display:inline;">
                                        <input type="hidden" name="appointmentId" value="<%= apt.getId() %>">
                                        <input type="hidden" name="status" value="Completed">
                                        <button type="submit" class="btn-small">Complete</button>
                                    </form>
                                <% } %>
                            </td>
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
