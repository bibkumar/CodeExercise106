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
 * Report Service.
 *
 * @author BibhavKumar
 */
public class ReportService {
    private final ManagerSalaryComparisonService managerSalaryComparisonService;
    private final EmployeeReportingLineCalculationService employeeReportingLineCalculationService;

    public ReportService(final ManagerSalaryComparisonService managerSalaryComparisonService, final EmployeeReportingLineCalculationService employeeReportingLineCalculationService) {
        this.managerSalaryComparisonService = managerSalaryComparisonService;
        this.employeeReportingLineCalculationService = employeeReportingLineCalculationService;
    }

    /**
     * Get underpaid managers with disparity amount.
     *
     * @param employees set of employees
     * @return Map with key as Employee id and Value is Disparity amount.
     */
    public Map<Long, BigDecimal> getUnderpaidManagersWithDisparity(final Set<Employee> employees) {
        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);
        return managers.stream()
                .filter(m -> Boolean.TRUE.equals(m.getEarningLess()))
                .collect(Collectors.toMap(Manager::getId, Manager::getByAmount));
    }

    /**
     * Get overpaid employees with disparity amount.
     *
     * @param employees set of employees
     * @return Map with key as Employee id and Value is Disparity amount.
     */
    public Map<Long, BigDecimal> getOverpaidManagersWithDisparity(final Set<Employee> employees) {
        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);
        return managers.stream()
                .filter(m -> Boolean.TRUE.equals(m.getEarningMore()))
                .collect(Collectors.toMap(Manager::getId, Manager::getByAmount));
    }

    /**
     * Get Employees with too long reporting line; employees who have more than 4 managers b/w them and CEO.
     *
     * @param employees set of employees
     * @return Map with key as Employee id and Value is Disparity in managers count b/w ceo and them.
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
