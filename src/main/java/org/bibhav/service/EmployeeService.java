package org.bibhav.service;

import org.bibhav.mapper.EmployeeMapper;
import org.bibhav.model.Employee;
import org.bibhav.model.EmployeeDto;
import org.bibhav.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Employee service.
 *
 * @author BibhavKumar
 */

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get employees from data source.
     *
     * @return Set of employees.
     */
    public Set<Employee> getEmployees() {
        List<EmployeeDto> allEmployees = employeeRepository.getEmployees();
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
