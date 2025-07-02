package org.bibhav.service;

import org.bibhav.model.entity.Employee;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Interface for generating reports based on employee and manager data.
 * Provides methods to analyze and retrieve insights such as salary disparities and reporting line lengths.
 * <p>
 * This service is designed to facilitate organizational analysis by identifying underpaid or overpaid managers,
 * and employees with excessively long reporting lines.
 * <p>
 * Typical use cases include salary analysis, reporting hierarchy evaluation, and identifying potential inefficiencies
 * in organizational structures.
 *
 * @author BibhavKumar
 */
public interface IReportService {
    /**
     * Retrieves a map of underpaid managers with salary disparities.
     * The key is the manager's ID, and the value is the amount by which they are underpaid.
     *
     * @param employees A set of Employee entities to analyze.
     * @return A map of underpaid managers with their respective salary disparities.
     */
    Map<Long, BigDecimal> getUnderpaidManagersWithDisparity(final Set<Employee> employees);

    /**
     * Retrieves a map of overpaid managers with salary disparities.
     * The key is the manager's ID, and the value is the amount by which they are overpaid.
     *
     * @param employees A set of Employee entities to analyze.
     * @return A map of overpaid managers with their respective salary disparities.
     */
    Map<Long, BigDecimal> getOverpaidManagersWithDisparity(final Set<Employee> employees);

    /**
     * Retrieves a map of employees with excessively long reporting lines.
     * The key is the employee's ID, and the value is the length of their reporting line.
     *
     * @param employees A set of Employee entities to analyze.
     * @return A map of employees with their respective reporting line lengths.
     */
    Map<Long, Integer> getEmployeesWithTooLongReportingLine(final Set<Employee> employees);

}
