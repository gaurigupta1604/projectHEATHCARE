# Healthcare Management System

## Overview
A comprehensive healthcare management system built with pure Java, JDBC, and Servlets. The application features role-based dashboards for Patients, Doctors, and Administrators with appointment booking, medical reports, and feedback management.

## Tech Stack
- **Language**: Java (JDK 11+)
- **Database**: H2 Database (MySQL compatibility mode)
- **Web Framework**: Java Servlets 4.0 + JSP
- **Server**: Embedded Jetty 9.4
- **Build Tool**: Maven
- **JDBC**: Direct JDBC for database operations

## Project Structure
```
healthcare-management-system/
├── src/main/java/com/healthcare/
│   ├── model/          # Entity classes (User, Patient, Doctor, Appointment, etc.)
│   ├── dao/            # Data Access Objects with JDBC
│   ├── controller/     # Servlets for handling requests
│   └── util/           # Database utilities and helpers
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── views/      # JSP files
│   │   └── web.xml     # Deployment descriptor
│   ├── css/            # Stylesheets
│   └── js/             # JavaScript files
└── pom.xml             # Maven dependencies
```

## Features
1. **Secure Authentication**: BCrypt password hashing, session fixation protection, role-based access
2. **Patient Dashboard**: Registration, doctor browsing, appointment booking, history
3. **Doctor Dashboard**: View appointments, mark completed, view patient list
4. **Admin Dashboard**: Manage doctors, view all patients/appointments/feedback
5. **Appointment System**: Book, view, and track appointments
6. **Feedback System**: Patient feedback with ratings
7. **Report Management**: Medical reports with role-based access
8. **Healthcare UI**: Professional medical color scheme (blues, whites, greens)

## Database Schema
- **users**: Authentication and role management
- **patients**: Patient profiles and details
- **doctors**: Doctor profiles with specializations
- **appointments**: Appointment bookings with status tracking
- **reports**: Medical reports linked to patients
- **feedback**: Patient feedback on appointments

## Recent Changes
- [2025-10-22] Initial project setup with Maven and dependencies
- [2025-10-22] Configured H2 database in MySQL compatibility mode
- [2025-10-22] Set up embedded Jetty server for Replit deployment
- [2025-10-22] Implemented all core features (dashboards, appointments, reports, feedback)
- [2025-10-22] **Security Enhancements**: 
  - Added BCrypt password hashing (jBCrypt 0.10.2) for secure password storage
  - Implemented session fixation protection in login flow
  - Secured default admin credentials with BCrypt hashing
- [2025-10-22] Created web.xml for explicit servlet mapping and index.jsp for root redirect

## User Preferences
- Strict adherence to Java JDBC approach (no ORM frameworks)
- MySQL syntax and conventions (via H2 MySQL mode)
- Traditional MVC pattern with Servlets and JSP
- Professional, medical-themed UI design

## Architecture Decisions
- **Database**: Using H2 in MySQL mode instead of pure MySQL for Replit compatibility
- **Server**: Embedded Jetty for easy deployment without external servlet container
- **Authentication**: Custom session-based auth with BCrypt password hashing for security
- **Servlet Mapping**: web.xml used for explicit servlet configuration and deployment
- **Payment**: Stripe integration deferred for future implementation

## Security Implementation
- **Password Hashing**: BCrypt (work factor 12) for all user passwords
- **Session Management**: Session fixation protection with session invalidation on login
- **Default Credentials**: Admin account uses BCrypt-hashed password (stored securely in database)
- **DAO Layer**: All password operations use BCrypt.hashpw() and BCrypt.checkpw()

## Demo Credentials
- **Admin**: username: `admin` / password: `admin123`
- **Note**: First-time users should register as patients, doctors are added by admin
