package org.bibhav;

import org.bibhav.model.Employee;
import org.bibhav.repository.FileEmployeeRepository;
import org.bibhav.service.EmployeeReportingLineCalculationService;
import org.bibhav.service.EmployeeService;
import org.bibhav.service.ManagerSalaryComparisonService;
import org.bibhav.service.ReportService;

import java.util.Map;
import java.util.Set;

/**
 * Entry point to the application.
 *
 * @author BibhavKumar
 */
public class Main {
    public static void main(String[] args) {
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository("src/main/resources/data.csv");
        EmployeeService employeeService = new EmployeeService(fileEmployeeRepository);
        Set<Employee> employees = employeeService.getEmployees();

        ManagerSalaryComparisonService managerSalaryComparisonService = new ManagerSalaryComparisonService();
        EmployeeReportingLineCalculationService employeeReportingLineCalculationService = new EmployeeReportingLineCalculationService();
        ReportService reportService = new ReportService(managerSalaryComparisonService, employeeReportingLineCalculationService);

        Map<Long, Double> underpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);
        System.out.println("Underpaid Managers With Disparity" + underpaidManagersWithDisparity);

        Map<Long, Double> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);
        System.out.println("Overpaid Managers With Disparity" + overpaidManagersWithDisparity);

        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);
        System.out.println("Employees With Too Long Reporting Line" + employeesWithTooLongReportingLine);
    }

}