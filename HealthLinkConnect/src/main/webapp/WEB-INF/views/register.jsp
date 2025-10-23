<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Healthcare Management System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <div class="container">
        <div class="auth-box">
            <div class="header">
                <h1>🏥 Healthcare System</h1>
                <h2>Patient Registration</h2>
            </div>
            
            <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("errorMessage") %>
                </div>
            <% } %>
            
            <form method="post" action="<%= request.getContextPath() %>/register">
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
                        <label>Phone</label>
                        <input type="tel" name="phone">
                    </div>
                    
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input type="date" name="dateOfBirth">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label>Gender</label>
                        <select name="gender">
                            <option value="">Select Gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>Blood Group</label>
                        <select name="bloodGroup">
                            <option value="">Select Blood Group</option>
                            <option value="A+">A+</option>
                            <option value="A-">A-</option>
                            <option value="B+">B+</option>
                            <option value="B-">B-</option>
                            <option value="AB+">AB+</option>
                            <option value="AB-">AB-</option>
                            <option value="O+">O+</option>
                            <option value="O-">O-</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label>Address</label>
                    <textarea name="address" rows="3"></textarea>
                </div>
                
                <button type="submit" class="btn-primary">Register</button>
            </form>
            
            <div class="auth-link">
                <p>Already have an account? <a href="<%= request.getContextPath() %>/login">Login</a></p>
            </div>
        </div>
    </div>
</body>
</html>
