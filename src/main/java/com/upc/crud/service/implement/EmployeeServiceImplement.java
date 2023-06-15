package com.upc.crud.service.implement;

import com.upc.crud.model.Employee;
import com.upc.crud.repository.EmployeeRepository;
import com.upc.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplement implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;//LLAMADA AL repository → EmployeeRepository

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        //return employeeRepository.findById(id).get();
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }
    //public Iterable<Employee> getEmployees() {
    //  return employeeRepository.findAll();
    //}

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public boolean isEmployeeExist(int id) {
        return employeeRepository.existsById(id);
    }
}
