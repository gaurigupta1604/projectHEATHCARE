package com.healthcare.controller;

import com.healthcare.dao.UserDAO;
import com.healthcare.dao.PatientDAO;
import com.healthcare.model.User;
import com.healthcare.model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;
    private PatientDAO patientDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String bloodGroup = request.getParameter("bloodGroup");

        try {
            User existingUser = userDAO.selectUserByUsername(username);
            if (existingUser != null) {
                request.setAttribute("errorMessage", "Username already exists");
                request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
                return;
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole("PATIENT");
            userDAO.insertUser(user);

            Patient patient = new Patient();
            patient.setUserId(user.getId());
            patient.setFullName(fullName);
            patient.setPhone(phone);
            patient.setAddress(address);
            if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
                patient.setDateOfBirth(Date.valueOf(dateOfBirth));
            }
            patient.setGender(gender);
            patient.setBloodGroup(bloodGroup);
            patientDAO.insertPatient(patient);

            request.setAttribute("successMessage", "Registration successful! Please login.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error during registration", e);
        }
    }
}
