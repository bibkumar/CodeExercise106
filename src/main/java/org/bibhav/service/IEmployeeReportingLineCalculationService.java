package org.bibhav.service;

import org.bibhav.model.entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for calculating employee reporting lines.
 * Provides methods to determine the reporting hierarchy for employees based on their relationships.
 * <p>
 * This service is responsible for generating mappings between employee IDs and their respective reporting lines,
 * enabling analysis of organizational structures and hierarchies.
 * <p>
 * Typical use cases include identifying reporting chains and analyzing reporting line lengths.
 *
 * @author BibhavKumar
 */
public interface IEmployeeReportingLineCalculationService {
    /**
     * Generates a mapping of employee IDs to their respective reporting line lists.
     * Each entry in the map contains an employee ID as the key and a list of IDs representing
     * the reporting line as the value.
     *
     * @param employees A set of Employee entities for which the reporting lines are to be calculated.
     * @return A map where each key is an employee ID and each value is a list of IDs representing their reporting line.
     */
    Map<Long, List<Long>> getEmployeeIdAndReportingLineListMap(final Set<Employee> employees);
}
