package org.bibhav.util;

public final class AppConstants {
    private AppConstants() {
        /* Don't Initialize */
    }

    public static final String EARNING_LESS_THRESHOLD_MULTIPLIER = "1.2";
    public static final String EARNING_MORE_THRESHOLD_MULTIPLIER = "1.5";
    public static final String OUTPUT_LINE_DECORATOR = "*******************************************%n";
    public static final String INVALID_DATA_FORMAT_ERROR = "Invalid data format in processing file";
    public static final String DUPLICATE_EMPLOYEE_FOUND = "Duplicate employee found in file";
    public static final String ERROR_OCCURRED_IN_READING_FILE = "Error occurred in reading file";
    public static final String EMPLOYEE_NOT_FOUND_MESSAGE_FORMAT = "No %s(s) found with the given condition.%n";
    public static final String EMPLOYEE_FOUND_MESSAGE_FORMAT = "%s with id [%s] %s [%s].%n";
    public static final String EARNING_LESS_HEADER_MESSAGE = "(1) which managers earn less than they should, and by how much%n";
    public static final String EARNING_MORE_HEADER_MESSAGE = "(2) which managers earn more than they should, and by how much%n";
    public static final String EMPLOYEE_LINE_TOO_LONG_HEADER_MESSAGE = "(3) which employees have a reporting line which is too long, and by how much%n";
    public static final String DATA_FILE_PATH_IS_REQUIRED_ERROR_MESSAGE = "Data file path is required as an argument.";
    public static final String CEO_CONFIGURATION_ERROR = "More than one CEO Or No CEO found in data file";
}
