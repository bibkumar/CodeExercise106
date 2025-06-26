package org.bibhav.mapper;

import org.bibhav.model.Employee;
import org.bibhav.model.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class EmployeeMapperTest {
    @Test
    void getEmployee() {
        Employee expectedEmployee = new Employee(124L, "Martin", "Chekov", 45000D, 123L);
        Employee actualEmployee = EmployeeMapper.getEmployee(new EmployeeDto(Arrays.asList("124", "Martin", "Chekov", "45000", "123")));

        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }
}