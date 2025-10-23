<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
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
        <h2>Admin Dashboard</h2>
        
        <div class="dashboard-stats">
            <div class="stat-card">
                <h3><%= request.getAttribute("totalPatients") %></h3>
                <p>Total Patients</p>
            </div>
            
            <div class="stat-card">
                <h3><%= request.getAttribute("totalDoctors") %></h3>
                <p>Total Doctors</p>
            </div>
            
            <div class="stat-card">
                <h3><%= request.getAttribute("totalAppointments") %></h3>
                <p>Total Appointments</p>
            </div>
        </div>
        
        <div class="admin-actions">
            <h3>Quick Actions</h3>
            <a href="<%= request.getContextPath() %>/admin/addDoctor" class="btn-primary">Add New Doctor</a>
            <a href="<%= request.getContextPath() %>/admin/doctors" class="btn-primary">Manage Doctors</a>
            <a href="<%= request.getContextPath() %>/admin/patients" class="btn-primary">View Patients</a>
            <a href="<%= request.getContextPath() %>/admin/appointments" class="btn-primary">View Appointments</a>
            <a href="<%= request.getContextPath() %>/admin/feedback" class="btn-primary">View Feedback</a>
        </div>
    </div>
</body>
</html>
