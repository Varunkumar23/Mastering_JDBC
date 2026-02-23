package com.lpu.jdbc.dao;

import com.lpu.jdbc.model.Employee;
import com.lpu.jdbc.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void insertEmployee(Employee employee) {
        String sql = "INSERT INTO employee(id,name,salary) VALUES(?,?,?)";
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setDouble(3, employee.getSalary());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Data inserted successfully.");

            } else {
                System.out.println("Data not inserted!.");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Salary: " + rs.getDouble("salary"));


            } else {
                System.out.println("Employee Not Found!");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public void getAllEmployees() {
        String sql = "SELECT * FROM employee";
        try (
                Connection connection = DBUtil.getConnection();

                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Salary: " + rs.getDouble("salary"));
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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
                System.out.println("Data updated successfully");
            } else {
                System.out.println("Data not updated.");

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


    public void transferSalary(int fromId, int toId, double amount) {
        String deductQuery = "UPDATE employee SET salary=salary-? WHERE id=?";
        String addQuery = "UPDATE employee SET salary=salary+? WHERE id=?";
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();


            try (
                    PreparedStatement deduction = connection.prepareStatement(deductQuery);
                    PreparedStatement addition = connection.prepareStatement(addQuery);
            ) {
                connection.setAutoCommit(false);

                deduction.setDouble(1, amount);
                deduction.setInt(2, fromId);
                deduction.executeUpdate();

//                int x=10/0;

                addition.setDouble(1, amount);
                addition.setInt(2, toId);
                addition.executeUpdate();
                connection.commit();


            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println("Transaction failed rolled back.");
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void insertMultipleEmployees(List<Employee> employees) {
        String sql = "INSERT INTO employee(id,name,salary) VALUES(?,?,?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            connection.setAutoCommit(false);

            for (Employee emp : employees) {
                ps.setInt(1, emp.getId());
                ps.setString(2, emp.getName());
                ps.setDouble(3, emp.getSalary());
                ps.addBatch();
            }

            int[] results = ps.executeBatch();
            connection.commit();
            System.out.println("Batch inserted successfully. Rows inserted " + results.length);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}
