package com.upc.crud.controller;

import com.upc.crud.exception.EmployeeNotFoundException;
import com.upc.crud.model.Employee;
import com.upc.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeServiceController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        employee = employeeService.createEmployee(employee);
        return  new ResponseEntity<>("Employee created successfully with ID → " + employee.getId(), HttpStatus.CREATED);
        //return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        boolean isEmployeeExist = employeeService.isEmployeeExist(id);
        if (isEmployeeExist) {
            employee.setId(id);
            employeeService.updateEmployee(employee);
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        } else {
            //return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            throw new EmployeeNotFoundException();
        }
        /*
        if (!employeeService.isEmployeeExist(id)) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        employee.setId(id);
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        */
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployees() {
        List<Employee> employeesList = employeeService.getEmployees();
        if (employeesList.isEmpty()) {
            return new ResponseEntity<>("Employees not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employeesList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteEmployees(@PathVariable("id") int id) {
        boolean isEmployeeExist = employeeService.isEmployeeExist(id);
        if (isEmployeeExist) {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } else {
            //return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            throw new EmployeeNotFoundException();
        }
        /*
        if (!employeeService.isEmployeeExist(id)) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        */
    }
}
