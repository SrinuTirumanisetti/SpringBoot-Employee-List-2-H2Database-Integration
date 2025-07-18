/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here

package com.example.employee.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.employee.service.EmployeeH2Service;
import com.example.employee.model.Employee;


@RestController
public class EmployeeController{

    @Autowired
    public EmployeeH2Service service;

    @GetMapping("/employees")
    public ArrayList<Employee> getEmployees(){
        return service.getEmployees();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        return service.addEmployee(employee);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId){
        return service.getEmployeeById(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable("employeeId") int employeeId){
        return service.updateEmployee(employeeId,employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") int employeeId){
        service.deleteEmployee(employeeId);
    }
}

