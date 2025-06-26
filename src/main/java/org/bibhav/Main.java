package org.bibhav;

import org.bibhav.model.Employee;
import org.bibhav.model.Manager;
import org.bibhav.repository.FileEmployeeRepository;
import org.bibhav.service.EmployeeReportingLineService;
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
        Set<Employee> employees = employeeService.fetchAllEmployees();

        ManagerSalaryComparisonService managerSalaryComparisonService = new ManagerSalaryComparisonService();
        Set<Manager> managersWithSalaryComparison = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);
        System.out.println("Managers With Salary Comparison " + managersWithSalaryComparison);

        EmployeeReportingLineService employeeReportingLineService = new EmployeeReportingLineService();
        ReportService reportService = new ReportService(managerSalaryComparisonService, employeeReportingLineService);

        Map<Long, Double> underpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);
        System.out.println("Underpaid Managers With Disparity" + underpaidManagersWithDisparity);

        Map<Long, Double> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);
        System.out.println("Overpaid Managers With Disparity" + overpaidManagersWithDisparity);

        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);
        System.out.println("Employees With Too Long Reporting Line" + employeesWithTooLongReportingLine);
    }

}