package org.bibhav.util;

import org.bibhav.model.Employee;
import org.bibhav.model.EmployeeDto;

/**
 * Employee utility class.
 *
 * @author BibhavKumar
 */
public class EmployeeUtility {
    private EmployeeUtility() {
        /* Don't initialize */
    }

    public static Employee getEmployeeFromEmployeeDto(final EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary(), employeeDto.getManagerId());
    }
}
