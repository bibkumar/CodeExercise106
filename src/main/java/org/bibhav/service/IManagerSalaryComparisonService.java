package org.bibhav.service;

import org.bibhav.model.entity.Employee;
import org.bibhav.model.entity.Manager;

import java.util.Set;

/**
 * Salary comparison service interface.
 *
 * @author BibhavKumar
 */
public interface IManagerSalaryComparisonService {
    Set<Manager> fetchManagersWithSalaryComparison(final Set<Employee> employees);
}
