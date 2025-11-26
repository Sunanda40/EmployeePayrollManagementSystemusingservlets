
package com.payroll.servlet;

import com.payroll.model.Employee;
import com.payroll.util.DButil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewEmployees")
public class ViewEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String search = request.getParameter("search");  // <-- Get search text from JSP
        List<Employee> list = new ArrayList<>();
        String sql;

        // If search is empty, show all employees
        if (search == null || search.trim().isEmpty()) {
            sql = "SELECT * FROM employee ORDER BY emp_id";
        } else {
            sql = "SELECT * FROM employee WHERE CAST(emp_id AS CHAR) LIKE ? OR emp_name LIKE ? OR designation LIKE ? ORDER BY emp_id";
        }

        try (Connection con = DButil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);

            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.trim() + "%";
                ps.setString(1, searchPattern);
                ps.setString(2, searchPattern);
                ps.setString(3, searchPattern);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee e = new Employee(
                            rs.getInt("emp_id"),
                            rs.getString("emp_name"),
                            rs.getString("designation"),
                            rs.getDouble("basic_salary"),
                            rs.getDouble("hra"),
                            rs.getDouble("da"),
                            rs.getDouble("deductions"),
                            rs.getDouble("net_salary")
                    );
                    list.add(e);
                }
            }

            request.setAttribute("employeeList", list);
            request.setAttribute("searchValue", search); // retain input in JSP
            request.getRequestDispatcher("ViewEmployee.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // POST just calls GET
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}