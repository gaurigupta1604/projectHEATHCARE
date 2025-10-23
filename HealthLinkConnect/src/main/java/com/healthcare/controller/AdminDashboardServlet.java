package com.healthcare.controller;

import com.healthcare.dao.*;
import com.healthcare.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/*")
public class AdminDashboardServlet extends HttpServlet {
    private UserDAO userDAO;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private AppointmentDAO appointmentDAO;
    private FeedbackDAO feedbackDAO;
    private ReportDAO reportDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        doctorDAO = new DoctorDAO();
        patientDAO = new PatientDAO();
        appointmentDAO = new AppointmentDAO();
        feedbackDAO = new FeedbackDAO();
        reportDAO = new ReportDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getPathInfo();
        try {
            switch (action == null ? "/dashboard" : action) {
                case "/dashboard":
                    showDashboard(request, response);
                    break;
                case "/doctors":
                    showDoctors(request, response);
                    break;
                case "/patients":
                    showPatients(request, response);
                    break;
                case "/appointments":
                    showAppointments(request, response);
                    break;
                case "/feedback":
                    showFeedback(request, response);
                    break;
                case "/addDoctor":
                    showAddDoctorForm(request, response);
                    break;
                case "/deleteDoctor":
                    deleteDoctor(request, response);
                    break;
                case "/reports":
                    showReports(request, response);
                    break;
                default:
                    showDashboard(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getPathInfo();
        try {
            switch (action == null ? "" : action) {
                case "/addDoctor":
                    addDoctor(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int totalPatients = patientDAO.selectAllPatients().size();
        int totalDoctors = doctorDAO.selectAllDoctors().size();
        int totalAppointments = appointmentDAO.selectAllAppointments().size();
        
        request.setAttribute("totalPatients", totalPatients);
        request.setAttribute("totalDoctors", totalDoctors);
        request.setAttribute("totalAppointments", totalAppointments);
        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }

    private void showDoctors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Doctor> doctors = doctorDAO.selectAllDoctors();
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/WEB-INF/views/admin/doctors.jsp").forward(request, response);
    }

    private void showPatients(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Patient> patients = patientDAO.selectAllPatients();
        request.setAttribute("patients", patients);
        request.getRequestDispatcher("/WEB-INF/views/admin/patients.jsp").forward(request, response);
    }

    private void showAppointments(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Appointment> appointments = appointmentDAO.selectAllAppointments();
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("/WEB-INF/views/admin/appointments.jsp").forward(request, response);
    }

    private void showFeedback(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Feedback> feedbacks = feedbackDAO.selectAllFeedback();
        request.setAttribute("feedbacks", feedbacks);
        request.getRequestDispatcher("/WEB-INF/views/admin/feedback.jsp").forward(request, response);
    }

    private void showReports(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Report> reports = reportDAO.selectAllReports();
        request.setAttribute("reports", reports);
        request.getRequestDispatcher("/WEB-INF/views/admin/reports.jsp").forward(request, response);
    }

    private void showAddDoctorForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/addDoctor.jsp").forward(request, response);
    }

    private void addDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String specialization = request.getParameter("specialization");
        String phone = request.getParameter("phone");
        String qualification = request.getParameter("qualification");
        int experienceYears = Integer.parseInt(request.getParameter("experienceYears"));
        double consultationFee = Double.parseDouble(request.getParameter("consultationFee"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("DOCTOR");
        userDAO.insertUser(user);

        Doctor doctor = new Doctor();
        doctor.setUserId(user.getId());
        doctor.setFullName(fullName);
        doctor.setSpecialization(specialization);
        doctor.setPhone(phone);
        doctor.setQualification(qualification);
        doctor.setExperienceYears(experienceYears);
        doctor.setConsultationFee(consultationFee);
        doctorDAO.insertDoctor(doctor);

        response.sendRedirect(request.getContextPath() + "/admin/doctors");
    }

    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("id"));
        Doctor doctor = doctorDAO.selectDoctorById(doctorId);
        if (doctor != null) {
            doctorDAO.deleteDoctor(doctorId);
            userDAO.deleteUser(doctor.getUserId());
        }
        response.sendRedirect(request.getContextPath() + "/admin/doctors");
    }
}
