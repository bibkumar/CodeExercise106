package org.example.repository;

import org.example.model.EmployeeDto;

import java.util.List;

public interface EmployeeRepository {

    List<EmployeeDto> getAllEmployees();
}
