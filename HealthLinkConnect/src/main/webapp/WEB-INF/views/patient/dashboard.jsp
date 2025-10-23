<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Dashboard</title>
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
        <h2>Welcome, <%= session.getAttribute("username") %>!</h2>
        
        <div class="dashboard-grid">
            <div class="card">
                <h3>👤 My Profile</h3>
                <% Patient patient = (Patient) request.getAttribute("patient"); %>
                <% if (patient != null) { %>
                    <p><strong>Name:</strong> <%= patient.getFullName() %></p>
                    <p><strong>Blood Group:</strong> <%= patient.getBloodGroup() != null ? patient.getBloodGroup() : "N/A" %></p>
                <% } %>
                <a href="<%= request.getContextPath() %>/patient/profile" class="btn-primary">View Profile</a>
            </div>
            
            <div class="card">
                <h3>🩺 Find Doctors</h3>
                <p>Browse our list of qualified doctors and book appointments</p>
                <a href="<%= request.getContextPath() %>/patient/doctors" class="btn-primary">View Doctors</a>
            </div>
            
            <div class="card">
                <h3>📅 My Appointments</h3>
                <% List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments"); %>
                <p>Total: <%= appointments != null ? appointments.size() : 0 %></p>
                <a href="<%= request.getContextPath() %>/patient/appointments" class="btn-primary">View All</a>
            </div>
            
            <div class="card">
                <h3>📋 Medical Reports</h3>
                <p>View your medical reports and history</p>
                <a href="<%= request.getContextPath() %>/patient/reports" class="btn-primary">View Reports</a>
            </div>
        </div>
        
        <div class="recent-section">
            <h3>Recent Appointments</h3>
            <% if (appointments != null && !appointments.isEmpty()) { %>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Status</th>
                            <th>Symptoms</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int count = 0; %>
                        <% for (Appointment apt : appointments) { %>
                            <% if (count++ >= 5) break; %>
                            <tr>
                                <td><%= apt.getAppointmentDate() %></td>
                                <td><%= apt.getAppointmentTime() %></td>
                                <td><span class="status <%= apt.getStatus().toLowerCase() %>"><%= apt.getStatus() %></span></td>
                                <td><%= apt.getSymptoms() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <p>No appointments found. <a href="<%= request.getContextPath() %>/patient/doctors">Book your first appointment</a></p>
            <% } %>
        </div>
    </div>
</body>
</html>
