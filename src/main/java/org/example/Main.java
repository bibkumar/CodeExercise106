package org.example;

import org.example.model.Employee;
import org.example.model.Manager;
import org.example.repository.FileEmployeeRepository;
import org.example.service.EmployeeReportingLineService;
import org.example.service.EmployeeService;
import org.example.service.ManagerSalaryComparisonService;
import org.example.service.ReportService;

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