package org.bibhav.repository;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.dto.EmployeeDto;

import java.util.List;

/**
 * Interface for accessing employee data from a data source.
 * <p>
 * This repository provides methods to retrieve employee information in the form of `EmployeeDto` objects,
 * which can be used for further processing and analysis within the application.
 * <p>
 * Typical use cases include fetching employee data for building organizational structures,
 * performing salary analysis, and evaluating reporting hierarchies.
 *
 * @author BibhavKumar
 */
public interface EmployeeRepository {

    /**
     * Retrieves a list of EmployeeDto objects representing employees.
     *
     * @return A list of EmployeeDto objects containing employee data.
     * @throws ApplicationException If an error occurs while accessing the data source.
     * @throws BadRequestException  If the data format is invalid or parsing fails.
     */
    List<EmployeeDto> getEmployeeDtoList() throws ApplicationException, BadRequestException;
}
