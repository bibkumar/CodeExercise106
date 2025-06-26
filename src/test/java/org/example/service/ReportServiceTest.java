package org.example.service;

import org.example.TestUtility;
import org.example.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportServiceTest {

    private static ReportService reportService;

    @BeforeEach
    void setUp() {
        ManagerSalaryComparisonService managerComparisonService = new ManagerSalaryComparisonService();
        reportService = new ReportService(managerComparisonService);
    }

    @Test
    void getUnderpaidManagersWithDisparity() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(45000D);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);

        assertEquals(Double.valueOf(15000), overpaidManagersWithDisparity.get(124L));
    }

    @Test
    void getUnderpaidManagersWithDisparity_empty() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(61000D);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);

        assertTrue(overpaidManagersWithDisparity.isEmpty());
    }

    @Test
    void getOverpaidManagersWithDisparity() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(76000D);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);

        assertEquals(Double.valueOf(1000), overpaidManagersWithDisparity.get(124L));
    }

    @Test
    void getOverpaidManagersWithDisparity_empty() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(61000D);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);

        assertTrue(overpaidManagersWithDisparity.isEmpty());
    }
}