package org.bibhav.service;

import org.bibhav.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Employee reporting line service.
 *
 * @author BibhavKumar
 */
public class EmployeeReportingLineService {

    /**
     * Get Employees except ceo with their manager hierarchy list except Ceo.
     *
     * @param employees
     * @return Map with key as Employee Id and value as list of Id of managers b/w the employee and Ceo.
     */
    Map<Long, List<Long>> getEmployeeReportingLine(final Set<Employee> employees) {
        Optional<Employee> ceoOptional = employees.stream().filter(e -> Objects.isNull(e.getManagerId())).findFirst();
        if (ceoOptional.isPresent()) {
            Map<Long, Employee> employeeMap = employees.stream().collect(Collectors.toMap(Employee::getId, e -> e));
            Employee ceo = ceoOptional.get();
            return employees.stream().filter(e -> !ceo.getId().equals(e.getId()))
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
        return Map.of();
    }
}
