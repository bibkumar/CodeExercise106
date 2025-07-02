package org.bibhav.util;

import org.bibhav.model.entity.Employee;
import org.bibhav.model.dto.EmployeeDto;

/**
 * Utility class for operations related to `Employee` entities and DTOs.
 * <p>
 * This class provides static methods to facilitate the conversion between
 * `Employee` entities and their corresponding Data Transfer Objects (DTOs).
 * It is designed to simplify and centralize common operations involving
 * `Employee` objects, ensuring consistency and reducing code duplication.
 * <p>
 * Note: This class is not meant to be instantiated.
 *
 * @author BibhavKumar
 */
public final class EmployeeUtility {
    private EmployeeUtility() {
        /* Don't initialize */
    }

    public static Employee getEmployeeFromEmployeeDto(final EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary(), employeeDto.getManagerId(), null);
    }
}
