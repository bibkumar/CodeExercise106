package org.bibhav.repository;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.dto.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to test functions of file based employee repository.
 *
 * @author BibhavKumar
 */
class FileEmployeeRepositoryTest {
    @Test
    void getEmployeeDtoList_absentFileShouldThrowApplicationException() throws ApplicationException {
        FileEmployeeRepository emptyFileEmployeeRepository = new FileEmployeeRepository("src/test/resources/absent_test_data.csv");
        ApplicationException applicationException = Assertions.assertThrows(ApplicationException.class, emptyFileEmployeeRepository::getEmployeeDtoList);

        assertEquals("Error occurred in reading file", applicationException.getMessage());
    }

    @Test
    void getEmployeeDtoList_emptyDataFileShouldReturnZeroRecords() throws ApplicationException, BadRequestException {
        FileEmployeeRepository emptyFileEmployeeRepository = new FileEmployeeRepository("src/test/resources/empty_test_data.csv");
        List<EmployeeDto> allEmployees = emptyFileEmployeeRepository.getEmployeeDtoList();

        assertEquals(0, allEmployees.size());
    }

    @Test
    void getEmployeeDtoList_shouldSkipFirstLineAsHeader() throws ApplicationException, BadRequestException {
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository("src/test/resources/only_header_test_data.csv");
        List<EmployeeDto> allEmployees = fileEmployeeRepository.getEmployeeDtoList();

        assertEquals(0, allEmployees.size());
    }

    @Test
    void getEmployeeDtoList_properFileTest() throws ApplicationException, BadRequestException {
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository("src/test/resources/test_data.csv");
        List<EmployeeDto> allEmployees = fileEmployeeRepository.getEmployeeDtoList();

        assertEquals(2, allEmployees.size());
    }
}