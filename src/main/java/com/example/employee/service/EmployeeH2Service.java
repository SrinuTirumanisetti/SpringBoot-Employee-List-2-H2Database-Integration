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

}


