package org.bibhav;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;
import org.bibhav.repository.EmployeeRepository;
import org.bibhav.repository.FileEmployeeRepository;
import org.bibhav.service.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import static org.bibhav.util.AppConstants.OUTPUT_LINE_DECORATOR;
import static org.bibhav.util.PrintUtility.printMapWithProperInformation;

/**
 * Entry point to the application.
 *
 * @author BibhavKumar
 */
public class Main {


    public static void main(final String[] args) throws ApplicationException, BadRequestException {
        validateArgs(args);
        String dataFilePath = args[0];
        System.out.println("Data file path: - " + dataFilePath);
        EmployeeRepository fileEmployeeRepository = new FileEmployeeRepository(dataFilePath);
        IEmployeeService employeeService = new EmployeeService(fileEmployeeRepository);
        Set<Employee> employees = employeeService.getEmployees();

        IManagerSalaryComparisonService managerSalaryComparisonService = new ManagerSalaryComparisonService();
        IEmployeeReportingLineCalculationService employeeReportingLineCalculationService = new EmployeeReportingLineCalculationService();
        ReportService reportService = new ReportService(managerSalaryComparisonService, employeeReportingLineCalculationService);

        System.out.println(OUTPUT_LINE_DECORATOR);
        System.out.println("(1) which managers earn less than they should, and by how much");
        Map<Long, BigDecimal> underpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);
        printMapWithProperInformation("Manager", underpaidManagersWithDisparity, "earns less than they should, and by");
        System.out.println(OUTPUT_LINE_DECORATOR);

        System.out.println("(2) which managers earn more than they should, and by how much");
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);
        printMapWithProperInformation("Manager", overpaidManagersWithDisparity, "earns more than they should, and by");
        System.out.println(OUTPUT_LINE_DECORATOR);

        System.out.println("(3) which employees have a reporting line which is too long, and by how much");
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);
        printMapWithProperInformation("Employee", employeesWithTooLongReportingLine, "have a reporting line which is too long, and by");
        System.out.println(OUTPUT_LINE_DECORATOR);
    }

    private static void validateArgs(String[] args) throws BadRequestException {
        if (args == null || args.length == 0 || args[0] == null || args[0].isEmpty()) {
            throw new BadRequestException("Data file path is required as an argument.");
        }
    }


}