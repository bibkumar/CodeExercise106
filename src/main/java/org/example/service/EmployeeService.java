package org.example.service;

import org.example.model.Employee;
import org.example.model.EmployeeDto;
import org.example.mapper.EmployeeMapper;
import org.example.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Set<Employee> fetchAllEmployees(){
        List<EmployeeDto> allEmployees = employeeRepository.getAllEmployees();
        return allEmployees.stream()
                .map(e -> {
                    Employee employee = EmployeeMapper.getEmployee(e);
                    Set<Employee> subordinates = allEmployees.stream()
                            .filter(s -> Objects.equals(employee.getId(), s.getManagerId()))
                            .map(EmployeeMapper::getEmployee)
                            .collect(Collectors.toSet());
                    employee.setSubOrdinates(subordinates);
                    return employee;
                })
                .collect(Collectors.toSet());
    }
}
