package org.bibhav.service;

import org.bibhav.model.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for calculating employee reporting lines within an organization.
 * <p>
 * This class provides functionality to determine the hierarchical reporting structure
 * for employees, excluding the CEO. It calculates the list of managers for each employee
 * up to but not including the CEO, enabling analysis of reporting line lengths and organizational
 * structure efficiency.
 * <p>
 * Typical use cases include identifying employees with excessively long reporting lines,
 * evaluating organizational hierarchy, and facilitating data-driven decision-making processes
 * related to reporting structures.
 *
 * @author BibhavKumar
 */
public class EmployeeReportingLineCalculationService implements IEmployeeReportingLineCalculationService {


    /**
     * Get Employees except ceo with their manager hierarchy list except Ceo.
     * This method is responsible for calculating, for each employee (except the CEO),
     * the list of their managers (by ID) up to but not including the CEO.
     *
     * @param employees set of employees
     * @return Map with key as Employee id and value as list of id of managers b/w the employee and Ceo.
     */
    public Map<Long, List<Long>> getEmployeeIdAndReportingLineListMap(final Set<Employee> employees) {
        Map<Long, Employee> employeeMap = employees.stream().collect(Collectors.toMap(Employee::getId, e -> e));
        return employees.stream()
                .filter(e -> Objects.nonNull(e.getManagerId()))
                .collect(Collectors.toMap(Employee::getId, employee -> {
                    List<Long> managerIds = new ArrayList<>();
                    Employee manager = employeeMap.get(employee.getManagerId());
                    while (!Objects.isNull(manager.getManagerId())) {
                        managerIds.add(manager.getId());
                        manager = employeeMap.get(manager.getManagerId());
                    }
                    return managerIds;
                }));
    }
}
