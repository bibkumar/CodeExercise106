package org.bibhav.service;

import org.bibhav.exception.BadRequestException;
import org.bibhav.model.entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Employee reporting line calculation service interface.
 *
 * @author BibhavKumar
 */
public interface IEmployeeReportingLineCalculationService {
    Map<Long, List<Long>> getEmployeeIdAndReportingLineListMap(final Set<Employee> employees) throws BadRequestException;
}
