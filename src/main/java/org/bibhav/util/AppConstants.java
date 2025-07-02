package org.bibhav.util;

/**
 * A utility class that provides application-wide constants.
 * <p>
 * This class is designed to centralize the definition of constants used throughout
 * the application, ensuring consistency and reducing duplication. It includes
 * error messages, formatting strings, and configuration values that are commonly
 * referenced across different components.
 * <p>
 * By using this class, developers can maintain a single source of truth for
 * constant values, making the application easier to manage and less prone to
 * errors caused by hardcoding or inconsistent definitions.
 * <p>
 * Note: This class is not meant to be instantiated.
 *
 * @author BibhavKumar
 */
public final class AppConstants {
    private AppConstants() {
        /* Prevent instantiation */
    }

    public static final String EARNING_LESS_THRESHOLD_MULTIPLIER = "1.2";
    public static final String EARNING_MORE_THRESHOLD_MULTIPLIER = "1.5";
    public static final String OUTPUT_LINE_DECORATOR = "*******************************************";
    public static final String INVALID_DATA_FORMAT_ERROR = "Invalid data format in processing file";
    public static final String DUPLICATE_EMPLOYEE_FOUND = "Duplicate employee found in file";
    public static final String ERROR_OCCURRED_IN_READING_FILE = "Error occurred in reading file";
    public static final String EMPLOYEE_NOT_FOUND_MESSAGE_FORMAT = "No %s(s) found with the given condition.";
    public static final String EMPLOYEE_FOUND_MESSAGE_FORMAT = "%s with id [%s] %s [%s].";
    public static final String EARNING_LESS_HEADER_MESSAGE = "(1) which managers earn less than they should, and by how much";
    public static final String EARNING_MORE_HEADER_MESSAGE = "(2) which managers earn more than they should, and by how much";
    public static final String EMPLOYEE_LINE_TOO_LONG_HEADER_MESSAGE = "(3) which employees have a reporting line which is too long, and by how much";
    public static final String DATA_FILE_PATH_IS_REQUIRED_ERROR_MESSAGE = "Data file path is required as an argument.";
    public static final String CEO_CONFIGURATION_ERROR = "More than one CEO Or No CEO found in data file";
}
