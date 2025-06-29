package org.bibhav.service;

import org.bibhav.TestUtility;
import org.bibhav.exception.ApplicationException;
import org.bibhav.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class to test functions related to employee reporting line.
 *
 * @author BibhavKumar
 */
class EmployeeReportingLineCalculationServiceTest {

    private static EmployeeReportingLineCalculationService employeeReportingLineCalculationService;

    @BeforeEach
    void setUp() {
        employeeReportingLineCalculationService = new EmployeeReportingLineCalculationService();
    }

    @Test
    void getEmployeeReportingLine_shortReportingLine() throws ApplicationException {
        Set<Employee> employees = TestUtility.getEmployeesWithCeoAndShortReportingLine();
        Map<Long, List<Long>> employeeReportingLine = employeeReportingLineCalculationService.getEmployeeIdAndReportingLineListMap(employees);

        assertEquals(2, employeeReportingLine.size());
        List<Long> managerLine = employeeReportingLine.get(301L);
        assertEquals(1, managerLine.size());
        assertTrue(managerLine.contains(300L));
    }

    @Test
    void getEmployeeReportingLine_longReportingLine() throws ApplicationException {
        Set<Employee> employees = TestUtility.getEmployeesWithCeoAndLongReportingLine();
        Map<Long, List<Long>> employeeReportingLine = employeeReportingLineCalculationService.getEmployeeIdAndReportingLineListMap(employees);

        assertEquals(6, employeeReportingLine.size());
        List<Long> managerLine = employeeReportingLine.get(305L);
        assertEquals(5, managerLine.size());
        assertTrue(managerLine.contains(300L));
        assertTrue(managerLine.contains(301L));
        assertTrue(managerLine.contains(302L));
        assertTrue(managerLine.contains(303L));
        assertTrue(managerLine.contains(304L));
    }
}