<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.healthcare.model.Doctor" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
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
            <h2>Book Appointment</h2>
            
            <form method="post" action="<%= request.getContextPath() %>/patient/book">
                <div class="form-group">
                    <label>Select Doctor *</label>
                    <select name="doctorId" required>
                        <option value="">Choose a doctor</option>
                        <% List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors"); %>
                        <% if (doctors != null) { %>
                            <% for (Doctor doc : doctors) { %>
                                <option value="<%= doc.getId() %>"><%= doc.getFullName() %> - <%= doc.getSpecialization() %></option>
                            <% } %>
                        <% } %>
                    </select>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label>Appointment Date *</label>
                        <input type="date" name="appointmentDate" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Appointment Time *</label>
                        <select name="appointmentTime" required>
                            <option value="">Select Time</option>
                            <option value="09:00 AM">09:00 AM</option>
                            <option value="10:00 AM">10:00 AM</option>
                            <option value="11:00 AM">11:00 AM</option>
                            <option value="12:00 PM">12:00 PM</option>
                            <option value="02:00 PM">02:00 PM</option>
                            <option value="03:00 PM">03:00 PM</option>
                            <option value="04:00 PM">04:00 PM</option>
                            <option value="05:00 PM">05:00 PM</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label>Symptoms/Reason for Visit *</label>
                    <textarea name="symptoms" rows="4" required></textarea>
                </div>
                
                <button type="submit" class="btn-primary">Book Appointment</button>
                <a href="<%= request.getContextPath() %>/patient/doctors" class="btn-secondary">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>
