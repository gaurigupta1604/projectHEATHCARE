<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Report" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Reports</title>
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
        <h2>Medical Reports</h2>
        
        <% List<Report> reports = (List<Report>) request.getAttribute("reports"); %>
        <% if (reports != null && !reports.isEmpty()) { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Patient ID</th>
                        <th>Doctor ID</th>
                        <th>Report Type</th>
                        <th>Report Date</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Report report : reports) { %>
                        <tr>
                            <td><%= report.getId() %></td>
                            <td><%= report.getPatientId() %></td>
                            <td><%= report.getDoctorId() %></td>
                            <td><%= report.getReportType() %></td>
                            <td><%= report.getReportDate() %></td>
                            <td><%= report.getReportDetails() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No reports found.</p>
        <% } %>
    </div>
</body>
</html>
