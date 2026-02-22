package com.lpu;


import com.lpu.jdbc.dao.EmployeeDAO;
import com.lpu.jdbc.model.Employee;
import com.lpu.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee1 = new Employee(1,"Varun", 100000);
        Employee employee2 = new Employee(2,"Tharun", 200000);
        Employee employee3 = new Employee(3,"Adithya", 30000);
        Employee employee4 = new Employee(4,"Hari", 50000);

//        employeeDAO.insertEmployee(employee1);
//        employeeDAO.insertEmployee(employee2);
//        employeeDAO.insertEmployee(employee3);
//        employeeDAO.insertEmployee(employee4);


//        System.out.println(employeeDAO.getEmployeeById(employee1.getId()));
        employeeDAO.deleteEmployee(employee4);


    }
}
