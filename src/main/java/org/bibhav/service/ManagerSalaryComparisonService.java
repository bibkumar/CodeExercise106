package org.bibhav.service;

import org.bibhav.model.entity.Employee;
import org.bibhav.model.entity.Manager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Salary comparison service.
 *
 * @author BibhavKumar
 */
public class ManagerSalaryComparisonService {
    /**
     * Fetch all managers with salary comparison with theirs subordinates.
     *
     * @param employees set of employees
     * @return Managers with salary comparison.
     */
    public Set<Manager> fetchManagersWithSalaryComparison(final Set<Employee> employees) {
        return employees.stream()
                .filter(e -> Objects.nonNull(e.getSubOrdinates()) && !e.getSubOrdinates().isEmpty())
                .map(m -> {
                    Manager manager = new Manager();
                    manager.setId(m.getId());
                    BigDecimal sal = m.getSalary();
                    manager.setSalary(sal);
                    BigDecimal sumOfSalaryOfSubordinates = m.getSubOrdinates().stream()
                            .map(Employee::getSalary)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal avg = sumOfSalaryOfSubordinates.divide(new BigDecimal(m.getSubOrdinates().size()), RoundingMode.DOWN);
                    manager.setAvgSubOrdinatesSalary(avg);
                    BigDecimal earningLessThreshold = avg.multiply(new BigDecimal("1.2")).setScale(2, RoundingMode.DOWN);
                    if (sal.compareTo(earningLessThreshold) < 1) {
                        manager.setEarningLess(true);
                        manager.setByAmount(earningLessThreshold.subtract(sal));
                    }
                    BigDecimal earningMoreThreshold = avg.multiply(new BigDecimal("1.5")).setScale(2, RoundingMode.DOWN);
                    if (sal.compareTo(earningMoreThreshold) > 0) {
                        manager.setEarningMore(true);
                        manager.setByAmount(sal.subtract(earningMoreThreshold));
                    }
                    return manager;
                })
                .collect(Collectors.toSet());
    }
}
