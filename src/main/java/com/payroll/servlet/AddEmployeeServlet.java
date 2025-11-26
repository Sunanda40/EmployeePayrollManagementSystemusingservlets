package com.payroll.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.payroll.util.DButil;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("emp_name");
        String designation = request.getParameter("designation");
        String basicStr = request.getParameter("basic_salary");

        double basic = 0;
        try {
            basic = Double.parseDouble(basicStr);
        } catch (NumberFormatException e) {
            basic = 0;
        }

        // simple allowance/deduction logic
        double hra = 0.20 * basic;
        double da = 0.10 * basic;
        double deductions = 0.05 * basic;
        double net = basic + hra + da - deductions;

        String sql = "insert into employee (emp_name, designation, basic_salary, hra, da, deductions, net_salary) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DButil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, designation);
            ps.setDouble(3, basic);
            ps.setDouble(4, hra);
            ps.setDouble(5, da);
            ps.setDouble(6, deductions);
            ps.setDouble(7, net);

            ps.executeUpdate();
            // redirect back to home or view page
            response.sendRedirect("viewEmployees");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}