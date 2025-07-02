package org.bibhav.util;

import java.util.Map;
import java.util.stream.Collectors;

import static org.bibhav.util.AppConstants.EMPLOYEE_FOUND_MESSAGE_FORMAT;
import static org.bibhav.util.AppConstants.EMPLOYEE_NOT_FOUND_MESSAGE_FORMAT;

/**
 * Utility class for formatting messages in the application.
 * <p>
 * This class provides static methods to format messages dynamically based on
 * input parameters such as actor names, employee details, and custom messages.
 * It is designed to simplify the generation of consistent and meaningful
 * messages for logging, reporting, or user-facing outputs.
 * <p>
 * Typical use cases include formatting messages for scenarios where employees
 * are found or not found based on specific conditions, ensuring clarity and
 * uniformity in message presentation.
 * <p>
 * Note: This class is not meant to be instantiated.
 *
 * @author BibhavKumar
 */
public final class MessageFormatUtility {

    private MessageFormatUtility() {
        /* Don't Initialize */
    }

    public static String formMessage(String actor, Map<Long, ? extends Number> employeeIdAndInfoMap, String message) {
        String messageString = employeeIdAndInfoMap.entrySet().stream()
                .map(entry -> String.format(EMPLOYEE_FOUND_MESSAGE_FORMAT, actor, entry.getKey(), message, entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
        if (messageString.isEmpty()) {
            return String.format(EMPLOYEE_NOT_FOUND_MESSAGE_FORMAT, actor);
        }
        return messageString;
    }
}
