package org.bibhav.util;

import java.util.Map;


/**
 * Print message utility class.
 *
 * @author BibhavKumar
 */
public class PrintUtility {
    private PrintUtility() {
        /* Don't Initialize */
    }

    public static void printMapWithProperInformation(String actor, Map<Long, ? extends Number> employeeIdAndInfoMap, String message) {
        if (employeeIdAndInfoMap.isEmpty()) {
            System.out.printf("No %s(s) found with the given condition.%n", actor);
        }
        employeeIdAndInfoMap.forEach((k, v) -> {
            System.out.printf("%s with id [%s] %s [%s].%n", actor, k, message, v);
        });
    }

}
