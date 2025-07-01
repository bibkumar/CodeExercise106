package org.bibhav.service;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;

import java.util.Set;

/**
 * Employee service interface.
 *
 * @author BibhavKumar
 */
public interface IEmployeeService {
    Set<Employee> getEmployees() throws ApplicationException, BadRequestException;
}
