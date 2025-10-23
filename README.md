# projectHEATHCARE

HealthLinkConnect - Healthcare Management System
A comprehensive web-based Healthcare Management System built with Java, JSP, Servlets, and H2 Database. This system provides seamless management of patients, doctors, appointments, and medical records with role-based access control.

🛠️ Technology Stack
Backend: Java 11, JSP, Servlets

Database: H2 Database (embedded, running in MySQL compatibility mode)

Server: Embedded Jetty Server (version 9.4.51.v20230217)

Build Tool: Maven

Security: BCrypt for password hashing

📋 Prerequisites
Before running this project, ensure you have the following installed:

Java Development Kit (JDK) 11 or higher

Apache Maven 3.6+

Git (for cloning the repository)

Verify Prerequisites
Bash

# Check Java version
java -version

# Check Maven version
mvn -version
🚀 Quick Setup Guide
Step 1: Get the Project Files
Clone the repository and navigate into the project directory (if applicable) or navigate directly to the directory containing pom.xml.

Bash

# Example: Clone the repository (Replace <repository-url> with the actual URL if needed)
git clone <repository-url>
cd HealthLinkConnect
Step 2: Build the Project
Use Maven to download dependencies, compile the Java source code, and prepare the application.

Bash

# Clean and compile the project
mvn clean compile
Step 3: Run the Application
The project uses the exec-maven-plugin to start the embedded Jetty server by executing the main application class.

Bash

# Start the server (Listens on port 5000 by default)
mvn exec:java -Dexec.mainClass="com.healthcare.Application"
Step 4: Access the Application
Once the server indicates it is running (listening on port 5000), open your web browser and navigate to:

http://localhost:5000
👤 Default Login Credentials
The system comes with a pre-configured admin account:

Role: Administrator

Username: admin

Password: admin123

🚨 Troubleshooting
Port Already in Use
If the default port 5000 is already in use, you may see an error. You can kill the process using the port (on Linux/macOS) or change the port in Application.java.

Bash

# Kill process using port 5000 (Linux/macOS)
lsof -ti:5000 | xargs kill -9
Database Issues (Database Reset)
The application uses an embedded H2 database which creates the files healthcare.mv.db and healthcare.trace.db in the project root. If you need to completely reset the database and data, remove these files.

Bash

# Remove database files to reset
rm healthcare.mv.db healthcare.trace.db

# Restart application to recreate tables and default user
mvn exec:java -Dexec.mainClass="com.healthcare.Application"
