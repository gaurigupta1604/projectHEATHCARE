package com.healthcare.controller;

import com.healthcare.dao.*;
import com.healthcare.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/patient/*")
public class PatientDashboardServlet extends HttpServlet {
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private AppointmentDAO appointmentDAO;
    private ReportDAO reportDAO;
    private FeedbackDAO feedbackDAO;

    @Override
    public void init() {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        appointmentDAO = new AppointmentDAO();
        reportDAO = new ReportDAO();
        feedbackDAO = new FeedbackDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"PATIENT".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getPathInfo();
        try {
            switch (action == null ? "/dashboard" : action) {
                case "/dashboard":
                    showDashboard(request, response);
                    break;
                case "/profile":
                    showProfile(request, response);
                    break;
                case "/doctors":
                    showDoctors(request, response);
                    break;
                case "/book":
                    showBookingForm(request, response);
                    break;
                case "/appointments":
                    showAppointments(request, response);
                    break;
                case "/reports":
                    showReports(request, response);
                    break;
                case "/feedback":
                    showFeedbackForm(request, response);
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
        if (session == null || !"PATIENT".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getPathInfo();
        try {
            switch (action == null ? "" : action) {
                case "/book":
                    bookAppointment(request, response);
                    break;
                case "/feedback":
                    submitFeedback(request, response);
                    break;
                case "/profile":
                    updateProfile(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/patient/dashboard");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer patientId = (Integer) request.getSession().getAttribute("patientId");
        if (patientId != null) {
            Patient patient = patientDAO.selectPatientById(patientId);
            List<Appointment> recentAppointments = appointmentDAO.selectAppointmentsByPatientId(patientId);
            request.setAttribute("patient", patient);
            request.setAttribute("appointments", recentAppointments);
        }
        request.getRequestDispatcher("/WEB-INF/views/patient/dashboard.jsp").forward(request, response);
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer patientId = (Integer) request.getSession().getAttribute("patientId");
        if (patientId != null) {
            Patient patient = patientDAO.selectPatientById(patientId);
            request.setAttribute("patient", patient);
        }
        request.getRequestDispatcher("/WEB-INF/views/patient/profile.jsp").forward(request, response);
    }

    private void showDoctors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Doctor> doctors = doctorDAO.selectAllDoctors();
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/WEB-INF/views/patient/doctors.jsp").forward(request, response);
    }

    private void showBookingForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String doctorId = request.getParameter("doctorId");
        if (doctorId != null) {
            Doctor doctor = doctorDAO.selectDoctorById(Integer.parseInt(doctorId));
            request.setAttribute("doctor", doctor);
        }
        List<Doctor> doctors = doctorDAO.selectAllDoctors();
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/WEB-INF/views/patient/book.jsp").forward(request, response);
    }

    private void showAppointments(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer patientId = (Integer) request.getSession().getAttribute("patientId");
        if (patientId != null) {
            List<Appointment> appointments = appointmentDAO.selectAppointmentsByPatientId(patientId);
            request.setAttribute("appointments", appointments);
        }
        request.getRequestDispatcher("/WEB-INF/views/patient/appointments.jsp").forward(request, response);
    }

    private void showReports(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer patientId = (Integer) request.getSession().getAttribute("patientId");
        if (patientId != null) {
            List<Report> reports = reportDAO.selectReportsByPatientId(patientId);
            request.setAttribute("reports", reports);
        }
        request.getRequestDispatcher("/WEB-INF/views/patient/reports.jsp").forward(request, response);
    }

    private void showFeedbackForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/patient/feedback.jsp").forward(request, response);
    }

    private void bookAppointment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Integer patientId = (Integer) request.getSession().getAttribute("patientId");
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String appointmentDate = request.getParameter("appointmentDate");
        String appointmentTime = request.getParameter("appointmentTime");
        String symptoms = request.getParameter("symptoms");

        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(Date.valueOf(appointmentDate));
        appointment.setAppointmentTime(appointmentTime);
        appointment.setStatus("Scheduled");
        appointment.setSymptoms(symptoms);

        appointmentDAO.insertAppointment(appointment);
        response.sendRedirect(request.getContextPath() + "/patient/appointments");
    }

    private void submitFeedback(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Integer patientId = (Integer) request.getSession().getAttribute("patientId");
        String doctorIdStr = request.getParameter("doctorId");
        String appointmentIdStr = request.getParameter("appointmentId");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comments = request.getParameter("comments");

        Feedback feedback = new Feedback();
        feedback.setPatientId(patientId);
        if (doctorIdStr != null && !doctorIdStr.isEmpty()) {
            feedback.setDoctorId(Integer.parseInt(doctorIdStr));
        }
        if (appointmentIdStr != null && !appointmentIdStr.isEmpty()) {
            feedback.setAppointmentId(Integer.parseInt(appointmentIdStr));
        }
        feedback.setRating(rating);
        feedback.setComments(comments);

        feedbackDAO.insertFeedback(feedback);
        response.sendRedirect(request.getContextPath() + "/patient/dashboard");
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Integer patientId = (Integer) request.getSession().getAttribute("patientId");
        if (patientId != null) {
            Patient patient = patientDAO.selectPatientById(patientId);
            patient.setFullName(request.getParameter("fullName"));
            patient.setPhone(request.getParameter("phone"));
            patient.setAddress(request.getParameter("address"));
            String dateOfBirth = request.getParameter("dateOfBirth");
            if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
                patient.setDateOfBirth(Date.valueOf(dateOfBirth));
            }
            patient.setGender(request.getParameter("gender"));
            patient.setBloodGroup(request.getParameter("bloodGroup"));
            patientDAO.updatePatient(patient);
        }
        response.sendRedirect(request.getContextPath() + "/patient/profile");
    }
}
