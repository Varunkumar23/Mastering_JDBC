package com.lpu.jdbc.dao;

import com.lpu.jdbc.model.Employee;
import com.lpu.jdbc.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public int insertEmployee(Employee employee) {
        String sql = "INSERT INTO employee(name,salary) VALUES(?,?)";
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = ps.getGeneratedKeys();
        ) {
            ps.setString(1, employee.getName());
            ps.setDouble(2, employee.getSalary());

            int rows = ps.executeUpdate();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (
                Connection connection = DBUtil.getConnection();

                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Employee emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary"));
                employees.add(emp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    public void updateEmployeeSalary(int id, double newSalary) {
        String sql = "UPDATE employee SET salary=? where id=?";
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, newSalary);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee updated successfully");
            } else {
                System.out.println("Employee not found.");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployee(Employee emp) {
        String sql = "DELETE FROM employee WHERE id=?";
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, emp.getId());
            int rows = ps.executeUpdate();


            if (rows > 0) {
                System.out.println("Employee deleted successfully");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
