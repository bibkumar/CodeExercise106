package org.bibhav;

import org.bibhav.exception.ApplicationException;
import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;
import org.bibhav.repository.EmployeeRepository;
import org.bibhav.repository.FileEmployeeRepository;
import org.bibhav.service.*;
import org.bibhav.util.MessageFormatUtility;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static org.bibhav.util.AppConstants.*;

/**
 * Entry point to the application.
 *
 * @author BibhavKumar
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(final String[] args) throws ApplicationException, BadRequestException {
        validateArgs(args);
        String dataFilePath = args[0];
        EmployeeRepository fileEmployeeRepository = new FileEmployeeRepository(dataFilePath);
        IEmployeeService employeeService = new EmployeeService(fileEmployeeRepository);
        Set<Employee> employees = employeeService.getEmployees();

        IManagerSalaryComparisonService managerSalaryComparisonService = new ManagerSalaryComparisonService();
        IEmployeeReportingLineCalculationService employeeReportingLineCalculationService = new EmployeeReportingLineCalculationService();
        IReportService reportService = new ReportService(managerSalaryComparisonService, employeeReportingLineCalculationService);

        LOGGER.info(OUTPUT_LINE_DECORATOR);
        LOGGER.info(EARNING_LESS_HEADER_MESSAGE);
        Map<Long, BigDecimal> underpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);
        String underPaidMessage = MessageFormatUtility.formMessage("Manager", underpaidManagersWithDisparity, "earns less than they should, and by");
        LOGGER.info(underPaidMessage);
        LOGGER.info(OUTPUT_LINE_DECORATOR);

        LOGGER.info(EARNING_MORE_HEADER_MESSAGE);
        Map<Long, BigDecimal> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);
        String overPaidMessage = MessageFormatUtility.formMessage("Manager", overpaidManagersWithDisparity, "earns more than they should, and by");
        LOGGER.info(overPaidMessage);
        LOGGER.info(OUTPUT_LINE_DECORATOR);

        LOGGER.info(EMPLOYEE_LINE_TOO_LONG_HEADER_MESSAGE);
        Map<Long, Integer> employeesWithTooLongReportingLine = reportService.getEmployeesWithTooLongReportingLine(employees);
        String employeeTooLongReportingLineMessage = MessageFormatUtility.formMessage("Employee", employeesWithTooLongReportingLine, "have a reporting line which is too long, and by");
        LOGGER.info(employeeTooLongReportingLineMessage);
        LOGGER.info(OUTPUT_LINE_DECORATOR);
    }

    private static void validateArgs(String[] args) throws BadRequestException {
        if (args == null || args.length == 0 || args[0] == null || args[0].isEmpty()) {
            throw new BadRequestException(DATA_FILE_PATH_IS_REQUIRED_ERROR_MESSAGE);
        }
    }


}