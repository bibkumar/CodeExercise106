package org.bibhav.service;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.dto.EmployeeDto;
import org.bibhav.model.entity.Employee;
import org.bibhav.repository.EmployeeRepository;
import org.bibhav.util.EmployeeUtility;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for managing employee data and building reporting structures.
 * <p>
 * This class provides functionality to retrieve employee information from a data source,
 * convert it into `Employee` entities, and establish hierarchical relationships by populating
 * each employee's subordinates.
 * <p>
 * Typical use cases include organizational structure analysis, reporting hierarchy evaluation,
 * and facilitating data-driven decision-making processes within an organization.
 *
 * @author BibhavKumar
 */

public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieves employee data from a data source, converts it into `Employee` entities,
     * and builds the reporting structure by populating each employee's subordinates.
     * <p>
     * This method leverages the `EmployeeRepository` to fetch raw employee data in the form of `EmployeeDto` objects.
     * It then transforms these DTOs into `Employee` entities using the `EmployeeUtility` class.
     * During the transformation, it establishes hierarchical relationships by identifying subordinates
     * for each employee based on their manager ID.
     * <p>
     * Typical use cases include:
     * <ul>
     *   <li>Analyzing organizational structures.</li>
     *   <li>Evaluating reporting hierarchies.</li>
     *   <li>Facilitating data-driven decision-making processes within an organization.</li>
     * </ul>
     *
     * @return A `Set` of `Employee` entities, each with their respective subordinates populated.
     * @throws ApplicationException If an error occurs while accessing the data source.
     * @throws BadRequestException  If the data format is invalid or parsing fails.
     */
    public Set<Employee> getEmployees() throws ApplicationException, BadRequestException {
        List<EmployeeDto> allEmployees = employeeRepository.getEmployeeDtoList();
        return allEmployees.stream()
                .map(e -> {
                    Employee employee = EmployeeUtility.getEmployeeFromEmployeeDto(e);
                    Set<Employee> subordinates = allEmployees.stream()
                            .filter(s -> Objects.equals(employee.getId(), s.getManagerId()))
                            .map(EmployeeUtility::getEmployeeFromEmployeeDto)
                            .collect(Collectors.toSet());
                    return employee.withAddedSubordinates(subordinates);
                })
                .collect(Collectors.toSet());
    }
}
