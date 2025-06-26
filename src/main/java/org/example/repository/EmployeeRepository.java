package org.example.repository;

import org.example.model.EmployeeDto;

import java.util.List;
/**
 * Employee Repository Interface.
 *
 * @author BibhavKumar
 */
public interface EmployeeRepository {

    List<EmployeeDto> getAllEmployees();
}
