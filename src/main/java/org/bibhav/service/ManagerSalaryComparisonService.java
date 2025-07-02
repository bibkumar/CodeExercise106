package org.bibhav.service;

import org.bibhav.model.entity.Employee;
import org.bibhav.model.entity.Manager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.bibhav.util.AppConstants.EARNING_LESS_THRESHOLD_MULTIPLIER;
import static org.bibhav.util.AppConstants.EARNING_MORE_THRESHOLD_MULTIPLIER;

/**
 * Service for comparing manager salaries relative to their subordinates.
 * <p>
 * This class implements the {@link IManagerSalaryComparisonService} interface and provides functionality
 * to analyze and compare the salaries of managers based on the average salary of their direct subordinates.
 * <p>
 * The service identifies managers who are either underpaid or overpaid compared to their subordinates,
 * and calculates the disparity amount for further analysis.
 * <p>
 * Typical use cases include identifying salary disparities, ensuring fair compensation structures,
 * and analyzing managerial salary trends within an organization.
 *
 * @author BibhavKumar
 */
public class ManagerSalaryComparisonService implements IManagerSalaryComparisonService {


    private static final int EARN_LESS_COMPARISON_OPERAND = 1;
    private static final int EARN_MORE_COMPARISON_OPERAND = 0;

    /**
     * Computes salary comparisons for managers relative to their direct subordinates.
     * <p>
     * This method analyzes the salary of each manager in the provided set of employees
     * and compares it to the average salary of their direct subordinates. It identifies
     * whether a manager is earning less or more than the expected thresholds based on
     * the average subordinate salary and calculates the disparity amount.
     * <p>
     * The thresholds for comparison are defined by constants:
     * {@code EARNING_LESS_THRESHOLD_MULTIPLIER} and {@code EARNING_MORE_THRESHOLD_MULTIPLIER}.
     * <p>
     * The resulting set of {@link Manager} objects includes detailed information about:
     * <ul>
     *   <li>Manager's salary</li>
     *   <li>Average salary of their subordinates</li>
     *   <li>Whether the manager earns less or more than the thresholds</li>
     *   <li>The amount by which the manager's salary deviates from the thresholds</li>
     * </ul>
     *
     * @param employees A {@code Set<Employee>} representing all employees in the organization.
     *                  Each employee may or may not have subordinates.
     * @return A {@code Set<Manager>} objects, each representing a manager with additional
     *         information about how their salary compares to the average salary of their direct reports.
     */
    public Set<Manager> fetchManagersWithSalaryComparison(final Set<Employee> employees) {
        return employees.stream()
                .filter(e -> Objects.nonNull(e.getSubordinates()) && !e.getSubordinates().isEmpty())
                .map(manager -> {
                    BigDecimal sal = manager.getSalary();
                    BigDecimal avg = getAverageSalaryOfSubordinates(manager);
                    BigDecimal earningLessThreshold = avg.multiply(new BigDecimal(EARNING_LESS_THRESHOLD_MULTIPLIER)).setScale(2, RoundingMode.DOWN);
                    BigDecimal byAmount = null;
                    Boolean earningLess = null;
                    Boolean earningMore = null;
                    if (sal.compareTo(earningLessThreshold) < EARN_LESS_COMPARISON_OPERAND) {
                        earningLess = true;
                        byAmount = earningLessThreshold.subtract(sal);
                    }
                    BigDecimal earningMoreThreshold = avg.multiply(new BigDecimal(EARNING_MORE_THRESHOLD_MULTIPLIER)).setScale(2, RoundingMode.DOWN);
                    if (sal.compareTo(earningMoreThreshold) > EARN_MORE_COMPARISON_OPERAND) {
                        earningMore = true;
                        byAmount = sal.subtract(earningMoreThreshold);
                    }
                    return new Manager(manager.getId(), sal, avg, earningLess, earningMore, byAmount);
                })
                .collect(Collectors.toSet());
    }

    private static BigDecimal getAverageSalaryOfSubordinates(final Employee manager) {
        BigDecimal sumOfSalaryOfSubordinates = manager.getSubordinates().stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sumOfSalaryOfSubordinates.divide(new BigDecimal(manager.getSubordinates().size()), RoundingMode.DOWN);
    }
}
