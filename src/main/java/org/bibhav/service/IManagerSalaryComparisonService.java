package org.bibhav.service;

import org.bibhav.model.entity.Employee;
import org.bibhav.model.entity.Manager;

import java.util.Set;

/**
 * Interface for comparing manager salaries.
 * Provides methods to analyze and compare salaries of managers based on employee data.
 * <p>
 * This service is designed to identify managers and perform salary comparisons,
 * enabling insights into managerial compensation structures.
 * <p>
 * Typical use cases include determining salary disparities and analyzing salary trends among managers.
 *
 * @author BibhavKumar
 */
public interface IManagerSalaryComparisonService {
    /**
     * Fetches a set of Manager entities with salary comparisons based on the provided employee data.
     * Each Manager entity includes details about their salary, average salary of subordinates,
     * and whether they earn less or more than their subordinates.
     *
     * @param employees A set of Employee entities from which manager salary comparisons are derived.
     * @return A set of Manager entities containing salary comparison details.
     */
    Set<Manager> fetchManagersWithSalaryComparison(final Set<Employee> employees);
}
