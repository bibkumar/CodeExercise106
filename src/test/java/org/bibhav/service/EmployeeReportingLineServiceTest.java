package org.bibhav.service;

import org.bibhav.TestUtility;
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
class EmployeeReportingLineServiceTest {

    private static EmployeeReportingLineService employeeReportingLineService;

    @BeforeEach
    void setUp() {
        employeeReportingLineService = new EmployeeReportingLineService();
    }

    @Test
    void getEmployeeReportingLine_shortReportingLine() {
        Set<Employee> employees = TestUtility.getEmployeesWithCeoAndShortReportingLine();
        Map<Long, List<Long>> employeeReportingLine = employeeReportingLineService.getEmployeeReportingLine(employees);

        assertEquals(2, employeeReportingLine.size());
        List<Long> managerLine = employeeReportingLine.get(301L);
        assertEquals(1, managerLine.size());
        assertTrue(managerLine.contains(300L));
    }

    @Test
    void getEmployeeReportingLine_longReportingLine() {
        Set<Employee> employees = TestUtility.getEmployeesWithCeoAndLongReportingLine();
        Map<Long, List<Long>> employeeReportingLine = employeeReportingLineService.getEmployeeReportingLine(employees);

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