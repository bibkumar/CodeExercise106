package org.bibhav.util;

import java.util.Map;

import static org.bibhav.util.AppConstants.EMPLOYEE_FOUND_MESSAGE_FORMAT;
import static org.bibhav.util.AppConstants.EMPLOYEE_NOT_FOUND_MESSAGE_FORMAT;


/**
 * Print message utility class.
 *
 * @author BibhavKumar
 */
public final class PrintUtility {


    private PrintUtility() {
        /* Don't Initialize */
    }

    public static void printMapWithProperInformation(String actor, Map<Long, ? extends Number> employeeIdAndInfoMap, String message) {
        if (employeeIdAndInfoMap.isEmpty()) {
            System.out.printf(EMPLOYEE_NOT_FOUND_MESSAGE_FORMAT, actor);
        }
        employeeIdAndInfoMap.forEach((k, v) -> System.out.printf(EMPLOYEE_FOUND_MESSAGE_FORMAT, actor, k, message, v));
    }

}
