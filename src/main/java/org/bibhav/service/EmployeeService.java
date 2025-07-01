package org.bibhav.service;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.dto.EmployeeDto;
import org.bibhav.model.entity.Employee;
import org.bibhav.repository.EmployeeRepository;
import org.bibhav.util.EmployeeUtility;

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
    public Set<Employee> getEmployees() throws ApplicationException, BadRequestException {
        List<EmployeeDto> allEmployees = employeeRepository.getEmployeeDtoList();
        return allEmployees.stream()
                .map(e -> {
                    Employee employee = EmployeeUtility.getEmployeeFromEmployeeDto(e);
                    Set<Employee> subordinates = allEmployees.stream()
                            .filter(s -> Objects.equals(employee.getId(), s.getManagerId()))
                            .map(EmployeeUtility::getEmployeeFromEmployeeDto)
                            .collect(Collectors.toSet());
                    return employee.withAddedSubordinates(subordinates);
                })
                .collect(Collectors.toSet());
    }
}
