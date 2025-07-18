/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 * 
 */

// Write your code here

package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeRowMapper;
import com.example.employee.repository.EmployeeRepository;
import java.util.*;

@Service
public class EmployeeH2Service implements EmployeeRepository{

    @Autowired
    public JdbcTemplate db;

    @Override
    public ArrayList<Employee> getEmployees(){
        List<Employee> employeeList = db.query("SELECT * FROM EMPLOYEELIST",new EmployeeRowMapper());
        return new ArrayList<>(employeeList);
    }

    @Override
    public Employee addEmployee(Employee employee){
        db.update("INSERT INTO EMPLOYEELIST(employeeName,email,department) VALUES(?,?,?);",
        employee.getEmployeeName(),employee.getEmail(),employee.getDepartment());

       Employee savedEmployee = 
       db.queryForObject(
                    "SELECT * FROM EMPLOYEELIST WHERE employeeName=? AND email=?",
                    new EmployeeRowMapper(),
                    employee.getEmployeeName(),
                    employee.getEmail()
        );

        return savedEmployee;
    }

    @Override
    public Employee getEmployeeById(int employeeId){
        try {
            return db.queryForObject(
                "SELECT * FROM EMPLOYEELIST WHERE employeeId=?",
                new EmployeeRowMapper(),
                employeeId
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }


    @Override
    public Employee updateEmployee(int employeeId,Employee employee){
        if(employee.getEmployeeName()!=null){
            db.update("UPDATE EMPLOYEELIST SET employeeName=? where employeeId=?",employee.getEmployeeName(),employeeId);
        }
        if(employee.getEmail()!=null){
            db.update("UPDATE EMPLOYEELIST SET email=? where employeeId=?",employee.getEmail(),employeeId);
        }
        if(employee.getDepartment()!=null){
            db.update("UPDATE EMPLOYEELIST SET department=? where employeeId=?",employee.getDepartment(),employeeId);
        }
        return getEmployeeById(employeeId);
    }

    @Override
    public void deleteEmployee(int employeeId){
        db.update("DELETE FROM EMPLOYEELIST WHERE employeeId=?",employeeId);
    }
}


