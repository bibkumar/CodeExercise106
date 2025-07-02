package org.bibhav.service;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;

import java.util.Set;

/**
 * Interface for employee-related operations and services.
 * Provides methods to interact with and retrieve employee data from the repository.
 * <p>
 * This service acts as a bridge between the repository layer and the business logic layer,
 * ensuring proper handling of application-level and request-level exceptions.
 *
 * @author BibhavKumar
 */
public interface IEmployeeService {
    /**
     * Retrieves a set of employees from the repository.
     *
     * @return A set of `Employee` entities.
     * @throws ApplicationException If an application-level error occurs during retrieval.
     * @throws BadRequestException  If the request data is invalid or malformed.
     */
    Set<Employee> getEmployees() throws ApplicationException, BadRequestException;
}
