package org.bibhav.service;

import org.bibhav.exception.ApplicationException;
import org.bibhav.model.Employee;

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
     * @param employees
     * @return Map with key as Employee Id and value as list of Id of managers b/w the employee and Ceo.
     */
    Map<Long, List<Long>> getEmployeeIdAndReportingLineListMap(final Set<Employee> employees) throws ApplicationException {
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

    private static Employee getCompanyCeo(final Set<Employee> employees) throws ApplicationException {
        Optional<Employee> ceoOptional = employees.stream()
                .filter(e -> Objects.isNull(e.getManagerId())).findFirst();
        if(ceoOptional.isEmpty()){
            throw new ApplicationException("Data source issue::CEO info not provided."); //Assumption 2 mentioned in readme file
        }
        Employee ceo = ceoOptional.get();
        return ceo;
    }
}
