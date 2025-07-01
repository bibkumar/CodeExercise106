package org.bibhav.repository;

import org.bibhav.exception.ApplicationException;
import org.bibhav.model.dto.EmployeeDto;

import java.util.List;
/**
 * Employee Repository Interface.
 *
 * @author BibhavKumar
 */
public interface EmployeeRepository {

    List<EmployeeDto> getEmployeeDtoList() throws ApplicationException;
}
