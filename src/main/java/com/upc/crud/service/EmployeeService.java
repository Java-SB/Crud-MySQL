package com.upc.crud.service;

import com.upc.crud.model.Employee;

import java.util.List;

public interface EmployeeService {
    public abstract Employee createEmployee(Employee employee);
    public abstract void updateEmployee(Employee employee);
    public abstract Employee getEmployee(int id);
    public abstract List<Employee> getEmployees();
    public abstract void deleteEmployee(int id);
    public abstract boolean isEmployeeExist(int id);
}
