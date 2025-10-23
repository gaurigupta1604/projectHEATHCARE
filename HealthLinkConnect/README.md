# HealthLinkConnect - Healthcare Management System

A comprehensive web-based Healthcare Management System built with Java, JSP, Servlets, and H2 Database. This system provides seamless management of patients, doctors, appointments, and medical records with role-based access control.

## 🏥 Features

### Admin Features
- **Dashboard**: Complete system overview and statistics
- **Doctor Management**: Add, view, and manage doctor profiles
- **Patient Management**: View and manage patient information
- **Appointment Management**: Monitor all appointments across the system
- **Feedback System**: Review patient feedback and ratings
- **Report Generation**: Generate and view medical reports

### Doctor Features
- **Personal Dashboard**: View appointments and patient information
- **Patient Management**: Access assigned patient records
- **Appointment Scheduling**: Manage appointment schedules
- **Profile Management**: Update personal and professional information

### Patient Features
- **Personal Dashboard**: View medical history and upcoming appointments
- **Doctor Directory**: Browse available doctors by specialization
- **Appointment Booking**: Schedule appointments with preferred doctors
- **Medical Reports**: Access personal medical reports
- **Feedback System**: Rate and review healthcare services
- **Profile Management**: Update personal information

## 🛠️ Technology Stack

- **Backend**: Java 11, JSP, Servlets
- **Frontend**: HTML5, CSS3, JavaScript, Bootstrap
- **Database**: H2 Database (embedded)
- **Server**: Embedded Jetty Server
- **Build Tool**: Maven
- **Security**: BCrypt for password hashing

## 📋 Prerequisites

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK) 11 or higher**
- **Apache Maven 3.6+**
- **Git** (for cloning the repository)

### Verify Prerequisites
```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check Git version
git --version
```

## 🚀 Quick Setup Guide

### Step 1: Clone the Repository
```bash
git clone <repository-url>
cd HealthLinkConnect
```

### Step 2: Build the Project
```bash
# Clean and compile the project
mvn clean compile

# Package the application (optional)
mvn package
```

### Step 3: Run the Application
```bash
# Start the server
mvn exec:java -Dexec.mainClass="com.healthcare.Application"
```

**Alternative method:**
```bash
# Compile and run directly
javac -cp "target/classes:target/dependency/*" src/main/java/com/healthcare/Application.java
java -cp "target/classes:target/dependency/*" com.healthcare.Application
```

### Step 4: Access the Application
Open your web browser and navigate to:
```
http://localhost:5000
```

## 👤 Default Login Credentials

The system comes with a pre-configured admin account:

- **Username**: `admin`
- **Password**: `admin123`
- **Role**: Administrator

## 📊 Database Information

The application uses an **embedded H2 database** that:
- Automatically creates all required tables on first startup
- Stores data in the project directory as `healthcare.mv.db`
- Runs in MySQL compatibility mode
- Includes sample admin account for immediate access

### Database Schema
The system automatically creates the following tables:
- `users` - User authentication and role management
- `patients` - Patient personal information
- `doctors` - Doctor profiles and specializations
- `appointments` - Appointment scheduling and management
- `reports` - Medical reports and documentation
- `feedback` - Patient feedback and ratings

## 🔧 Configuration

### Port Configuration
The default server port is `5000`. To change it, modify the `port` variable in `Application.java`:

```java
int port = 5000; // Change to your desired port
```

### Database Configuration
Database settings can be found in `DatabaseUtil.java`:
- **URL**: `jdbc:h2:./healthcare;MODE=MySQL;DB_CLOSE_DELAY=-1`
- **Username**: `sa`
- **Password**: (empty)

## 📁 Project Structure

```
HealthLinkConnect/
├── src/main/
│   ├── java/com/healthcare/
│   │   ├── Application.java              # Main application entry point
│   │   ├── controller/                   # Servlet controllers
│   │   │   ├── LoginServlet.java
│   │   │   ├── RegisterServlet.java
│   │   │   ├── AdminDashboardServlet.java
│   │   │   ├── DoctorDashboardServlet.java
│   │   │   └── PatientDashboardServlet.java
│   │   ├── dao/                          # Data Access Objects
│   │   │   ├── UserDAO.java
│   │   │   ├── PatientDAO.java
│   │   │   ├── DoctorDAO.java
│   │   │   ├── AppointmentDAO.java
│   │   │   ├── ReportDAO.java
│   │   │   └── FeedbackDAO.java
│   │   ├── model/                        # Entity classes
│   │   │   ├── User.java
│   │   │   ├── Patient.java
│   │   │   ├── Doctor.java
│   │   │   ├── Appointment.java
│   │   │   ├── Report.java
│   │   │   └── Feedback.java
│   │   └── util/
│   │       └── DatabaseUtil.java         # Database connection utility
│   └── webapp/                           # Web resources
│       ├── index.jsp                     # Landing page
│       ├── css/style.css                 # Stylesheet
│       └── WEB-INF/
│           ├── web.xml                   # Web configuration
│           └── views/                    # JSP view templates
│               ├── login.jsp
│               ├── register.jsp
│               ├── admin/                # Admin views
│               ├── doctor/               # Doctor views
│               └── patient/              # Patient views
├── pom.xml                               # Maven configuration
└── README.md                             # This file
```

## 🔄 Development Workflow

### Making Changes
1. **Backend Changes**: Modify Java files in `src/main/java/`
2. **Frontend Changes**: Update JSP files in `src/main/webapp/WEB-INF/views/`
3. **Styling Changes**: Edit CSS in `src/main/webapp/css/`

### Rebuilding
```bash
# After making changes, recompile
mvn clean compile

# Restart the server
mvn exec:java -Dexec.mainClass="com.healthcare.Application"
```

## 🧪 Testing

### Manual Testing
1. Start the application
2. Navigate to `http://localhost:5000`
3. Login with admin credentials
4. Test different user workflows:
   - Register new patients and doctors
   - Book appointments
   - Generate reports
   - Submit feedback

### Database Testing
```bash
# Access H2 Console (if enabled in development)
# Add this to your browser: http://localhost:5000/h2-console
# JDBC URL: jdbc:h2:./healthcare
# User: sa
# Password: (leave empty)
```

## 🚨 Troubleshooting

### Common Issues

**Port Already in Use:**
```bash
# Kill process using port 5000
lsof -ti:5000 | xargs kill -9

# Or change port in Application.java
```

**Maven Build Errors:**
```bash
# Clear Maven cache
mvn dependency:purge-local-repository

# Rebuild
mvn clean install
```

**Database Issues:**
```bash
# Remove database files to reset
rm healthcare.mv.db healthcare.trace.db

# Restart application to recreate tables
```

**Java Version Issues:**
```bash
# Ensure Java 11+ is being used
export JAVA_HOME=/path/to/java11
mvn clean compile exec:java
```

## 📝 Usage Examples

### Registering a New Doctor
1. Login as admin
2. Navigate to "Manage Doctors"
3. Click "Add New Doctor"
4. Fill in doctor details and specialization
5. The doctor can now login with their credentials

### Booking an Appointment
1. Login as patient
2. Go to "Book Appointment"
3. Select doctor and preferred date/time
4. Add symptoms/notes
5. Confirm booking

### Generating Reports
1. Login as doctor
2. Navigate to patient records
3. Select patient
4. Create new report with findings
5. Report is saved and accessible to patient

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support and questions:
- Create an issue in the repository
- Check the troubleshooting section above
- Review the project documentation

## 🔮 Future Enhancements

- [ ] Email notifications for appointments
- [ ] SMS integration for reminders
- [ ] Advanced reporting and analytics
- [ ] Mobile responsive design improvements
- [ ] File upload for medical documents
- [ ] Online payment integration
- [ ] Telemedicine video consultation
- [ ] Multi-language support

---

**Happy Coding! 🏥💻**