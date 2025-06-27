package org.bibhav.repository;

import org.bibhav.model.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test class to test functions of file based employee repository.
 *
 * @author BibhavKumar
 */
class FileEmployeeRepositoryTest {

    private static FileEmployeeRepository fileEmployeeRepository;

    @BeforeAll
    static void setUp() {
        fileEmployeeRepository = new FileEmployeeRepository("src/test/resources/test_data.csv");
    }

    @Test
    void getAllEmployees() {
        List<EmployeeDto> allEmployees = fileEmployeeRepository.getEmployees();

        Assertions.assertEquals(2, allEmployees.size());
    }
}