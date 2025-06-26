package org.bibhav.repository;

import org.bibhav.model.EmployeeDto;

import java.util.List;
/**
 * Employee Repository Interface.
 *
 * @author BibhavKumar
 */
public interface EmployeeRepository {

    List<EmployeeDto> getAllEmployees();
}
