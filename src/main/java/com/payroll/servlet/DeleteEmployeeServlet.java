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

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("emp_id");
        int id = 0;

        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("deleteEmployee.html?error=Invalid+Employee+ID");
            return;
        }

        String sql = "DELETE FROM employee WHERE emp_id=?";
        try (Connection con = DButil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                                response.sendRedirect("deleteEmployee.html?success=Employee+deleted+successfully");
            } else {
                response.sendRedirect("deleteEmployee.html?error=Employee+not+found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("deleteEmployee.html?error=Something+went+wrong");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("deleteEmployee.html");
    }
}