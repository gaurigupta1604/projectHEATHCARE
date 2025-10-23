<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Report" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Medical Reports</title>
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
        <h2>Medical Reports</h2>
        
        <% List<Report> reports = (List<Report>) request.getAttribute("reports"); %>
        
        <% if (reports != null && !reports.isEmpty()) { %>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Report Type</th>
                        <th>Report Date</th>
                        <th>Details</th>
                        <th>Created</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Report report : reports) { %>
                        <tr>
                            <td><%= report.getId() %></td>
                            <td><%= report.getReportType() %></td>
                            <td><%= report.getReportDate() %></td>
                            <td><%= report.getReportDetails() %></td>
                            <td><%= report.getCreatedAt() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No medical reports found.</p>
        <% } %>
    </div>
</body>
</html>
