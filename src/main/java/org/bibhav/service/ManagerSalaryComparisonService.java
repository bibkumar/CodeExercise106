package org.bibhav.service;

import org.bibhav.model.Employee;
import org.bibhav.model.Manager;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Salary comparison service.
 *
 * @author BibhavKumar
 */
public class ManagerSalaryComparisonService {
    public Set<Manager> fetchManagersWithSalaryComparison(final Set<Employee> employees) {
        return employees.stream()
                .filter(e -> Objects.nonNull(e.getSubOrdinates()) && !e.getSubOrdinates().isEmpty())
                .map(m -> {
                    Double sal = m.getSalary();
                    double avg = m.getSubOrdinates().stream().mapToDouble(Employee::getSalary).average().orElse(0);
                    Manager manager = new Manager();
                    manager.setSalary(sal);
                    manager.setId(m.getId());
                    manager.setAvgSubOrdinatesSalary(avg);
                    if (Double.compare(sal, avg * 1.2) != 1) { //earning less
                        manager.setEarningLess(true);
                        manager.setByAmount(avg * 1.2 - sal);
                    }
                    if (Double.compare(sal, avg * 1.5) == 1) { //earning more
                        manager.setEarningMore(true);
                        manager.setByAmount(sal - avg * 1.5);
                    }
                    return manager;
                })
                .collect(Collectors.toSet());
    }
}
