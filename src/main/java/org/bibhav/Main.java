package org.bibhav;

import org.bibhav.exception.ApplicationException;
import org.bibhav.model.entity.Employee;
import org.bibhav.repository.EmployeeRepository;
import org.bibhav.repository.FileEmployeeRepository;
import org.bibhav.service.EmployeeReportingLineCalculationService;
import org.bibhav.service.EmployeeService;
import org.bibhav.service.ManagerSalaryComparisonService;
import org.bibhav.service.ReportService;

import java.util.Map;
import java.util.Set;

import static org.bibhav.util.PrintUtility.printMapWithProperInformation;

/**
 * Entry point to the application.
 *
 * @author BibhavKumar
 */
public class Main {
    public static void main(final String[] args) throws ApplicationException {
        String dataFilePath = args[0];
        System.out.println("Data file path: - " + dataFilePath);
        EmployeeRepository fileEmployeeRepository = new FileEmployeeRepository(dataFilePath);
        EmployeeService employeeService = new EmployeeService(fileEmployeeRepository);
        Set<Employee> employees = employeeService.getEmployees();

        ManagerSalaryComparisonService managerSalaryComparisonService = new ManagerSalaryComparisonService();
        EmployeeReportingLineCalculationService employeeReportingLineCalculationService = new EmployeeReportingLineCalculationService();
        ReportService reportService = new ReportService(managerSalaryComparisonService, employeeReportingLineCalculationService);

        System.out.println("*******************************************");
        System.out.println("(1) which managers earn less than they should, and by how much");
        Map<Long, Double> underpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);
        printMapWithProperInformation("Manager", underpaidManagersWithDisparity, "earns less than they should, and by");
        System.out.println("*******************************************");

        System.out.println("(2) which managers earn more than they should, and by how much");
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);
        printMapWithProperInformation("Manager", overpaidManagersWithDisparity, "earns more than they should, and by");
        System.out.println("*******************************************");

        System.out.println("(3) which employees have a reporting line which is too long, and by how much");
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);
        printMapWithProperInformation("Employee", employeesWithTooLongReportingLine, "have a reporting line which is too long, and by");
        System.out.println("*******************************************");
    }


}