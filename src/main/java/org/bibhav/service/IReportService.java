package org.bibhav.service;

import org.bibhav.model.entity.Employee;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Report Service interface.
 *
 * @author BibhavKumar
 */
public interface IReportService {
    Map<Long, BigDecimal> getUnderpaidManagersWithDisparity(final Set<Employee> employees);

    Map<Long, BigDecimal> getOverpaidManagersWithDisparity(final Set<Employee> employees);

    Map<Long, Integer> getEmployeesWithTooLongReportingLine(final Set<Employee> employees);

}
