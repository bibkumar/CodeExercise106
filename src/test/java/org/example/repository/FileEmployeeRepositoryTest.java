package org.example.repository;

import org.example.model.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class FileEmployeeRepositoryTest {

    private static FileEmployeeRepository fileEmployeeRepository;

    @BeforeAll
    static void setUp() {
        fileEmployeeRepository = new FileEmployeeRepository("src/test/resources/test_data.csv");
    }

    @Test
    void getAllEmployees() {
        List<EmployeeDto> allEmployees = fileEmployeeRepository.getAllEmployees();

        Assertions.assertEquals(2, allEmployees.size());
    }
}