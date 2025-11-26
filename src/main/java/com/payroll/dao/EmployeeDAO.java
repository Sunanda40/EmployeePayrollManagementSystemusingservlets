package com.payroll.dao;
import java.sql.*;
import java.util.*;
import com.payroll.model.Employee;
import com.payroll.util.DButil;

public class EmployeeDAO {

    // Add Employee
    public int addEmployee(Employee emp) {
        int status = 0;
        try (Connection con = DButil.getConnection()) {
            String sql = "INSERT INTO employee (emp_name, designation, basic_salary, hra, da, deductions, net_salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getEmpName());
            ps.setString(2, emp.getDesignation());
            ps.setDouble(3, emp.getBasicSalary());
            ps.setDouble(4, emp.getHra());
            ps.setDouble(5, emp.getDa());
            ps.setDouble(6, emp.getDeductions());
            ps.setDouble(7, emp.getNetSalary());
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // View Employees
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (Connection con = DButil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setEmpName(rs.getString("emp_name"));
                emp.setDesignation(rs.getString("designation"));
                emp.setBasicSalary(rs.getDouble("basic_salary"));
                emp.setHra(rs.getDouble("hra"));
                emp.setDa(rs.getDouble("da"));
                emp.setDeductions(rs.getDouble("deductions"));
                emp.setNetSalary(rs.getDouble("net_salary"));
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Delete Employee
    public int deleteEmployee(int empId) {
        int status = 0;
        try (Connection con = DButil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM employee WHERE emp_id=?");
            ps.setInt(1, empId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Update Employee Salary
    public int UpdateSalary(int empId, double newSalary) {
        int status = 0;
        try (Connection con = DButil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("update employee set basic_salary=? WHERE emp_id=?");
            ps.setDouble(1, newSalary);
            ps.setInt(2, empId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}