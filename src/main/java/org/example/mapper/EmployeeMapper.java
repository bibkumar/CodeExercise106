package org.example.mapper;

import org.example.model.Employee;
import org.example.model.EmployeeDto;

/**
 * Employee Mapper utility class.
 *
 * @author BibhavKumar
 */
public class EmployeeMapper {
    private EmployeeMapper() {
        /* Don't initialize */
    }

    public static Employee getEmployee(final EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary(), employeeDto.getManagerId());
    }
}
