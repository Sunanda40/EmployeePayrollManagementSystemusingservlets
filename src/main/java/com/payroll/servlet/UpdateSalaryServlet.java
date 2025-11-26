package com.payroll.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.payroll.util.DButil;
import java.sql.Connection;
import java.sql.PreparedStatement;


@WebServlet("/updateSalary")
public class UpdateSalaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("emp_id");
        String basicStr = request.getParameter("basic_salary");

        int id = 0;
        double basic = 0;
        try {
            id = Integer.parseInt(idStr);
            basic = Double.parseDouble(basicStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("updateSalary.html?error=Invalid+input");
            return;
        }

        double hra = 0.20 * basic;
        double da = 0.10 * basic;
        double deductions = 0.05 * basic;
        double net = basic + hra + da - deductions;

        String sql = "update employee set basic_salary=?, hra=?, da=?, deductions=?, net_salary=? WHERE emp_id=?";
        try (Connection con = DButil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, basic);
            ps.setDouble(2, hra);
            ps.setDouble(3, da);
            ps.setDouble(4, deductions);
            ps.setDouble(5, net);
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                // success — employee updated
                response.sendRedirect("updateSalary.html?success=Salary+updated+successfully");
            } else {
                // employee not found
                response.sendRedirect("updateSalary.html?error=Employee+not+found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("updateSalary.html?error=Something+went+wrong");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("viewEmployees");
    }
}
