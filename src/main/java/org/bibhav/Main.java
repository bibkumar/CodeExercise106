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
 * <p>
 * This class serves as the main driver for the application, orchestrating the initialization
 * of services, loading of data, and execution of business logic. It is responsible for
 * validating input arguments, processing employee data, and generating reports based on
 * specific conditions such as salary comparisons and reporting line analysis.
 * <p>
 * The `Main` class leverages various utility and service classes to ensure modularity,
 * maintainability, and clarity in the application's workflow. It outputs formatted
 * messages and logs to provide insights into the application's operations and results.
 * <p>
 * Note: This class is designed to be executed as a standalone application.
 *
 * @author BibhavKumar
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Main method to run the application.
     * <p>
     * This method validates the input arguments, initializes the necessary repositories and services,
     * retrieves employee data, and generates reports based on salary comparisons and reporting line lengths.
     * It logs the results to the console for user visibility.
     *
     * @param args Command-line arguments, where the first argument is expected to be the path to the data file.
     * @throws ApplicationException If an error occurs during application execution.
     * @throws BadRequestException  If the input arguments are invalid or missing required information.
     */
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

    /**
     * Validates the input arguments for the application.
     * <p>
     * This method checks if the provided arguments are valid, specifically ensuring that
     * the first argument (data file path) is not null or empty. If validation fails,
     * it throws a BadRequestException with an appropriate error message.
     *
     * @param args The command-line arguments passed to the application.
     * @throws BadRequestException If the data file path is not provided or is invalid.
     */
    private static void validateArgs(String[] args) throws BadRequestException {
        if (args == null || args.length == 0 || args[0] == null || args[0].isEmpty()) {
            throw new BadRequestException(DATA_FILE_PATH_IS_REQUIRED_ERROR_MESSAGE);
        }
    }


}