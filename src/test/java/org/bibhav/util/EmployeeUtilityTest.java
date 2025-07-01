package org.bibhav.util;

import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;
import org.bibhav.model.dto.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Test class to test functions of employee utility.
 *
 * @author BibhavKumar
 */
class EmployeeUtilityTest {
    @Test
    void getEmployeeFromEmployeeDto() throws BadRequestException {
        Employee expectedEmployee = new Employee(124L, "Martin", "Chekov", new BigDecimal("45000"), 123L);
        Employee actualEmployee = EmployeeUtility.getEmployeeFromEmployeeDto(new EmployeeDto(Arrays.asList("124", "Martin", "Chekov", "45000", "123")));

        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }
}