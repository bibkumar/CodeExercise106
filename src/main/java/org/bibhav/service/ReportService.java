package org.bibhav.service;

import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;
import org.bibhav.model.entity.Manager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for generating reports based on employee and manager data.
 * <p>
 * This class implements the {@link IReportService} interface and provides functionality
 * to analyze and retrieve insights such as salary disparities and reporting line lengths.
 * <p>
 * The service facilitates organizational analysis by identifying underpaid or overpaid managers,
 * and employees with excessively long reporting lines, enabling better decision-making regarding
 * compensation structures and organizational efficiency.
 * <p>
 * Typical use cases include salary analysis, reporting hierarchy evaluation, and identifying potential inefficiencies
 * in organizational structures.
 *
 * @author BibhavKumar
 */
public class ReportService implements IReportService {
    private final IManagerSalaryComparisonService managerSalaryComparisonService;
    private final IEmployeeReportingLineCalculationService employeeReportingLineCalculationService;

    /**
     * Constructor for ReportService.
     *
     * @param managerSalaryComparisonService          Service for comparing manager salaries.
     * @param employeeReportingLineCalculationService Service for calculating employee reporting lines.
     */
    public ReportService(final IManagerSalaryComparisonService managerSalaryComparisonService, final IEmployeeReportingLineCalculationService employeeReportingLineCalculationService) {
        this.managerSalaryComparisonService = managerSalaryComparisonService;
        this.employeeReportingLineCalculationService = employeeReportingLineCalculationService;
    }

    /**
     * Retrieves a map of underpaid managers along with the amount by which they are underpaid.
     * <p>
     * This method analyzes the salaries of managers relative to their subordinates and identifies
     * those who earn less than the defined threshold. The threshold is calculated based on the
     * average salary of the manager's direct subordinates and a predefined multiplier.
     * <p>
     * The resulting map contains the manager's ID as the key and the disparity amount as the value.
     * <p>
     * Typical use cases include identifying salary disparities to ensure fair compensation structures
     * within the organization.
     *
     * @param employees A set of {@link Employee} entities representing all employees in the organization.
     *                  Each employee may or may not have subordinates.
     * @return A {@code Map<Long, BigDecimal>} where the key is the manager's ID and the value is the
     * amount by which they are underpaid.
     */
    public Map<Long, BigDecimal> getUnderpaidManagersWithDisparity(final Set<Employee> employees) {
        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);
        return managers.stream()
                .filter(m -> Boolean.TRUE.equals(m.getEarningLess()))
                .collect(Collectors.toMap(Manager::getId, Manager::getByAmount));
    }

    /**
     * Retrieves a map of overpaid managers along with the amount by which they are overpaid.
     * <p>
     * This method analyzes the salaries of managers relative to their subordinates and identifies
     * those who earn more than the defined threshold. The threshold is calculated based on the
     * average salary of the manager's direct subordinates and a predefined multiplier.
     * <p>
     * The resulting map contains the manager's ID as the key and the disparity amount as the value.
     * <p>
     * Typical use cases include identifying salary disparities to ensure fair compensation structures
     * within the organization.
     *
     * @param employees A set of {@link Employee} entities representing all employees in the organization.
     *                  Each employee may or may not have subordinates.
     * @return A {@code Map<Long, BigDecimal>} where the key is the manager's ID and the value is the
     * amount by which they are overpaid.
     */
    public Map<Long, BigDecimal> getOverpaidManagersWithDisparity(final Set<Employee> employees) {
        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);
        return managers.stream()
                .filter(m -> Boolean.TRUE.equals(m.getEarningMore()))
                .collect(Collectors.toMap(Manager::getId, Manager::getByAmount));
    }

    /**
     * Retrieves a map of employees with excessively long reporting lines.
     * <p>
     * This method identifies employees who have more than 4 managers between them and the CEO,
     * indicating potential inefficiencies in the organizational hierarchy. The disparity is calculated
     * as the number of managers exceeding the threshold of 4.
     * <p>
     * The resulting map contains the employee's ID as the key and the disparity in the number of managers
     * as the value.
     * <p>
     * Typical use cases include evaluating reporting hierarchies and identifying employees who may be
     * affected by overly complex reporting structures.
     *
     * @param employees A {@code Set<Employee>} representing all employees in the organization.
     *                  Each employee may or may not have a reporting line to the CEO.
     * @return A {@code Map<Long, Integer>} where the key is the employee's ID and the value is the
     * disparity in the number of managers between the employee and the CEO.
     * @throws BadRequestException if the input data is invalid or cannot be processed.
     */
    public Map<Long, Integer> getEmployeesWithTooLongReportingLine(final Set<Employee> employees) throws BadRequestException {
        Map<Long, List<Long>> employeeReportingLine = employeeReportingLineCalculationService.getEmployeeIdAndReportingLineListMap(employees);
        Map<Long, Integer> employeesWithTooLongReportingLine = new HashMap<>();
        employeeReportingLine.forEach((key, value) -> {
            if (value.size() > 4) {
                employeesWithTooLongReportingLine.put(key, value.size() - 4);
            }
        });
        return employeesWithTooLongReportingLine;
    }

}
