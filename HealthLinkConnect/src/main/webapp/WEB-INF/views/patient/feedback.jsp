<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submit Feedback</title>
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
        <div class="auth-box">
            <h2>Submit Feedback</h2>
            
            <form method="post" action="<%= request.getContextPath() %>/patient/feedback">
                <div class="form-group">
                    <label>Rating (1-5 stars) *</label>
                    <select name="rating" required>
                        <option value="">Select Rating</option>
                        <option value="5">⭐⭐⭐⭐⭐ (Excellent)</option>
                        <option value="4">⭐⭐⭐⭐ (Good)</option>
                        <option value="3">⭐⭐⭐ (Average)</option>
                        <option value="2">⭐⭐ (Below Average)</option>
                        <option value="1">⭐ (Poor)</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label>Comments *</label>
                    <textarea name="comments" rows="5" required placeholder="Share your experience with us..."></textarea>
                </div>
                
                <input type="hidden" name="doctorId" value="">
                <input type="hidden" name="appointmentId" value="">
                
                <button type="submit" class="btn-primary">Submit Feedback</button>
                <a href="<%= request.getContextPath() %>/patient/dashboard" class="btn-secondary">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>
