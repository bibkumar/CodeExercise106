package org.bibhav.service;

import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Employee reporting line calculation service.
 *
 * @author BibhavKumar
 */
public class EmployeeReportingLineCalculationService {

    /**
     * Get Employees except ceo with their manager hierarchy list except Ceo.
     *
     * @param employees set of employees
     * @return Map with key as Employee id and value as list of id of managers b/w the employee and Ceo.
     */
    Map<Long, List<Long>> getEmployeeIdAndReportingLineListMap(final Set<Employee> employees) throws BadRequestException {
        Employee ceo = getCompanyCeo(employees);
        Map<Long, Employee> employeeMap = employees.stream().collect(Collectors.toMap(Employee::getId, e -> e));
        return employees.stream()
                .filter(e -> !Objects.equals(ceo.getId(), e.getId()))
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

    private Employee getCompanyCeo(final Set<Employee> employees) throws BadRequestException {
        Optional<Employee> ceoOptional = employees.stream()
                .filter(e -> Objects.isNull(e.getManagerId())).findFirst();
        if (ceoOptional.isEmpty()) {
            throw new BadRequestException("Data source issue::CEO info not provided.");
        }
        return ceoOptional.get();
    }
}
