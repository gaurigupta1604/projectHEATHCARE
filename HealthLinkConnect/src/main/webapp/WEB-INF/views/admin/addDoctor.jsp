<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Doctor</title>
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
        <div class="auth-box">
            <h2>Add New Doctor</h2>
            
            <form method="post" action="<%= request.getContextPath() %>/admin/addDoctor">
                <div class="form-row">
                    <div class="form-group">
                        <label>Username *</label>
                        <input type="text" name="username" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Password *</label>
                        <input type="password" name="password" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label>Email *</label>
                    <input type="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label>Full Name *</label>
                    <input type="text" name="fullName" required>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label>Specialization *</label>
                        <input type="text" name="specialization" required placeholder="e.g., Cardiologist">
                    </div>
                    
                    <div class="form-group">
                        <label>Phone *</label>
                        <input type="tel" name="phone" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label>Qualification *</label>
                    <input type="text" name="qualification" required placeholder="e.g., MBBS, MD">
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label>Experience (Years) *</label>
                        <input type="number" name="experienceYears" required min="0">
                    </div>
                    
                    <div class="form-group">
                        <label>Consultation Fee ($) *</label>
                        <input type="number" name="consultationFee" required min="0" step="0.01">
                    </div>
                </div>
                
                <button type="submit" class="btn-primary">Add Doctor</button>
                <a href="<%= request.getContextPath() %>/admin/doctors" class="btn-secondary">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>
