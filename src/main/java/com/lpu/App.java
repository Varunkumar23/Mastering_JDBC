package com.lpu;


import com.lpu.jdbc.dao.EmployeeDAO;
import com.lpu.jdbc.model.Employee;
import com.lpu.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee1 = new Employee(1, "Varun", 100000);
        Employee employee2 = new Employee(2, "Tharun", 200000);
        Employee employee3 = new Employee(3, "Adithya", 30000);
        Employee employee4 = new Employee(4, "Hari", 50000);

//        employeeDAO.insertEmployee(employee1);
//        employeeDAO.insertEmployee(employee2);
//        employeeDAO.insertEmployee(employee3);
//        employeeDAO.insertEmployee(employee4);

//        employeeDAO.getEmployeeById(employee1.getId());
//        employeeDAO.updateEmployeeSalary(employee1.getId(),2000000);
//        System.out.println("Varun after updating salary");
//        employeeDAO.getEmployeeById(employee1.getId());

//        System.out.println(employeeDAO.getAllEmployees());
//        employeeDAO.transferSalary(employee1.getId(),employee2.getId(),350000);
//        System.out.println(employeeDAO.getAllEmployees());
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee(5,"Lohith",234543));
        employees.add(new Employee(6,"Nikhil",900004));
        employees.add(new Employee(7,"Ganesh",123456));
        employeeDAO.insertMultipleEmployees(employees);
        employeeDAO.getAllEmployees();


    }
}
