package com.healthcare.controller;

import com.healthcare.dao.UserDAO;
import com.healthcare.dao.PatientDAO;
import com.healthcare.dao.DoctorDAO;
import com.healthcare.model.User;
import com.healthcare.model.Patient;
import com.healthcare.model.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDAO.authenticateUser(username, password);
            if (user != null) {
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());

                if ("PATIENT".equals(user.getRole())) {
                    Patient patient = patientDAO.selectPatientByUserId(user.getId());
                    if (patient != null) {
                        session.setAttribute("patientId", patient.getId());
                    }
                    response.sendRedirect(request.getContextPath() + "/patient/dashboard");
                } else if ("DOCTOR".equals(user.getRole())) {
                    Doctor doctor = doctorDAO.selectDoctorByUserId(user.getId());
                    if (doctor != null) {
                        session.setAttribute("doctorId", doctor.getId());
                    }
                    response.sendRedirect(request.getContextPath() + "/doctor/dashboard");
                } else if ("ADMIN".equals(user.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                }
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error during login", e);
        }
    }
}
