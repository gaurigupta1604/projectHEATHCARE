package com.healthcare.controller;

import com.healthcare.dao.*;
import com.healthcare.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/doctor/*")
public class DoctorDashboardServlet extends HttpServlet {
    private DoctorDAO doctorDAO;
    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;
    private ReportDAO reportDAO;

    @Override
    public void init() {
        doctorDAO = new DoctorDAO();
        appointmentDAO = new AppointmentDAO();
        patientDAO = new PatientDAO();
        reportDAO = new ReportDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"DOCTOR".equals(session.getAttribute("role"))) {
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
                case "/appointments":
                    showAppointments(request, response);
                    break;
                case "/patients":
                    showPatients(request, response);
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
        if (session == null || !"DOCTOR".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getPathInfo();
        try {
            switch (action == null ? "" : action) {
                case "/updateStatus":
                    updateAppointmentStatus(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/doctor/dashboard");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer doctorId = (Integer) request.getSession().getAttribute("doctorId");
        if (doctorId != null) {
            Doctor doctor = doctorDAO.selectDoctorById(doctorId);
            List<Appointment> appointments = appointmentDAO.selectAppointmentsByDoctorId(doctorId);
            request.setAttribute("doctor", doctor);
            request.setAttribute("appointments", appointments);
        }
        request.getRequestDispatcher("/WEB-INF/views/doctor/dashboard.jsp").forward(request, response);
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer doctorId = (Integer) request.getSession().getAttribute("doctorId");
        if (doctorId != null) {
            Doctor doctor = doctorDAO.selectDoctorById(doctorId);
            request.setAttribute("doctor", doctor);
        }
        request.getRequestDispatcher("/WEB-INF/views/doctor/profile.jsp").forward(request, response);
    }

    private void showAppointments(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer doctorId = (Integer) request.getSession().getAttribute("doctorId");
        if (doctorId != null) {
            List<Appointment> appointments = appointmentDAO.selectAppointmentsByDoctorId(doctorId);
            request.setAttribute("appointments", appointments);
        }
        request.getRequestDispatcher("/WEB-INF/views/doctor/appointments.jsp").forward(request, response);
    }

    private void showPatients(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer doctorId = (Integer) request.getSession().getAttribute("doctorId");
        if (doctorId != null) {
            List<Appointment> appointments = appointmentDAO.selectAppointmentsByDoctorId(doctorId);
            Set<Integer> patientIds = new HashSet<>();
            for (Appointment apt : appointments) {
                patientIds.add(apt.getPatientId());
            }
            
            List<Patient> allPatients = patientDAO.selectAllPatients();
            allPatients.removeIf(p -> !patientIds.contains(p.getId()));
            request.setAttribute("patients", allPatients);
        }
        request.getRequestDispatcher("/WEB-INF/views/doctor/patients.jsp").forward(request, response);
    }

    private void showReports(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer doctorId = (Integer) request.getSession().getAttribute("doctorId");
        if (doctorId != null) {
            List<Report> reports = reportDAO.selectReportsByDoctorId(doctorId);
            request.setAttribute("reports", reports);
        }
        request.getRequestDispatcher("/WEB-INF/views/doctor/reports.jsp").forward(request, response);
    }

    private void updateAppointmentStatus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        String status = request.getParameter("status");
        appointmentDAO.updateAppointmentStatus(appointmentId, status);
        response.sendRedirect(request.getContextPath() + "/doctor/appointments");
    }
}
