<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Feedback" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Feedback</title>
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
        <h2>Patient Feedback</h2>
        
        <% List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks"); %>
        <% if (feedbacks != null && !feedbacks.isEmpty()) { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Patient ID</th>
                        <th>Doctor ID</th>
                        <th>Rating</th>
                        <th>Comments</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Feedback fb : feedbacks) { %>
                        <tr>
                            <td><%= fb.getId() %></td>
                            <td><%= fb.getPatientId() %></td>
                            <td><%= fb.getDoctorId() != null ? fb.getDoctorId() : "N/A" %></td>
                            <td><%= "⭐".repeat(fb.getRating()) %> (<%= fb.getRating() %>/5)</td>
                            <td><%= fb.getComments() %></td>
                            <td><%= fb.getCreatedAt() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No feedback found.</p>
        <% } %>
    </div>
</body>
</html>
