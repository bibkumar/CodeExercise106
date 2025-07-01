package org.bibhav.util;

import java.util.Map;
import java.util.stream.Collectors;

import static org.bibhav.util.AppConstants.EMPLOYEE_FOUND_MESSAGE_FORMAT;
import static org.bibhav.util.AppConstants.EMPLOYEE_NOT_FOUND_MESSAGE_FORMAT;


/**
 * Message format utility class.
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
