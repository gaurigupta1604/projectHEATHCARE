<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Healthcare Management System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <div class="container">
        <div class="auth-box">
            <div class="header">
                <h1>🏥 Healthcare System</h1>
                <h2>Login</h2>
            </div>
            
            <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("errorMessage") %>
                </div>
            <% } %>
            
            <% if (request.getAttribute("successMessage") != null) { %>
                <div class="alert alert-success">
                    <%= request.getAttribute("successMessage") %>
                </div>
            <% } %>
            
            <form method="post" action="<%= request.getContextPath() %>/login">
                <div class="form-group">
                    <label>Username</label>
                    <input type="text" name="username" required>
                </div>
                
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" required>
                </div>
                
                <button type="submit" class="btn-primary">Login</button>
            </form>
            
            <div class="auth-link">
                <p>Don't have an account? <a href="<%= request.getContextPath() %>/register">Register as Patient</a></p>
            </div>
            
            <div class="demo-credentials">
                <p><strong>Demo Credentials:</strong></p>
                <p>Admin: admin / admin123</p>
            </div>
        </div>
    </div>
</body>
</html>
