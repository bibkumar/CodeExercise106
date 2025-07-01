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
public class ManagerSalaryComparisonService implements IManagerSalaryComparisonService{
    /**
     * Fetch all managers with salary comparison with theirs subordinates.
     *
     * @param employees set of employees
     * @return Managers with salary comparison.
     */
    public Set<Manager> fetchManagersWithSalaryComparison(final Set<Employee> employees) {
        return employees.stream()
                .filter(e -> Objects.nonNull(e.getSubordinates()) && !e.getSubordinates().isEmpty())
                .map(m -> {
                    BigDecimal sal = m.getSalary();
                    BigDecimal sumOfSalaryOfSubordinates = m.getSubordinates().stream()
                            .map(Employee::getSalary)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal avg = sumOfSalaryOfSubordinates.divide(new BigDecimal(m.getSubordinates().size()), RoundingMode.DOWN);
                    BigDecimal earningLessThreshold = avg.multiply(new BigDecimal("1.2")).setScale(2, RoundingMode.DOWN);
                    BigDecimal byAmount = null;
                    Boolean earningLess = null;
                    Boolean earningMore = null;
                    if (sal.compareTo(earningLessThreshold) < 1) {
                        earningLess = true;
                        byAmount = earningLessThreshold.subtract(sal);
                    }
                    BigDecimal earningMoreThreshold = avg.multiply(new BigDecimal("1.5")).setScale(2, RoundingMode.DOWN);
                    if (sal.compareTo(earningMoreThreshold) > 0) {
                        earningMore = true;
                        byAmount = sal.subtract(earningMoreThreshold);
                    }
                    return new Manager(m.getId(), sal, avg, earningLess, earningMore, byAmount);
                })
                .collect(Collectors.toSet());
    }
}
