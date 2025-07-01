package org.bibhav.service;

import org.bibhav.TestUtility;
import org.bibhav.model.entity.Employee;
import org.bibhav.model.entity.Manager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test functions of manager salary comparison service.
 *
 * @author BibhavKumar
 */
class ManagerSalaryComparisonServiceTest {
    private static ManagerSalaryComparisonService managerSalaryComparisonService;

    @BeforeAll
    static void setUp() {
        managerSalaryComparisonService = new ManagerSalaryComparisonService();
    }

    @Test
    void fetchManagersWithSalaryComparison_ManagerEarningLess() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(new BigDecimal("45000"));

        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);

        assertEquals(1, managers.size());
        Optional<Manager> optionalManager = managers.stream().findFirst();
        assertTrue(optionalManager.isPresent());
        Manager manager = optionalManager.get();
        assertEquals(124L, manager.getId());
        assertEquals(new BigDecimal("45000"), manager.getSalary());
        assertTrue(manager.getEarningLess());
        assertNull(manager.getEarningMore());
        assertEquals(new BigDecimal("15000.00"), manager.getByAmount());
    }


    @Test
    void fetchManagersWithSalaryComparison_ManagerEarningMore() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(new BigDecimal("76000"));

        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);

        assertEquals(1, managers.size());
        Optional<Manager> optionalManager = managers.stream().findFirst();
        assertTrue(optionalManager.isPresent());
        Manager manager = optionalManager.get();
        assertEquals(124L, manager.getId());
        assertEquals(new BigDecimal("76000"), manager.getSalary());
        assertTrue(manager.getEarningMore());
        assertNull(manager.getEarningLess());
        assertEquals(new BigDecimal("1000.00"), manager.getByAmount());
    }

    @Test
    void fetchManagersWithSalaryComparison_ManagerEarningNormal() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(new BigDecimal("60001"));

        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);

        assertEquals(1, managers.size());
        Optional<Manager> optionalManager = managers.stream().findFirst();
        assertTrue(optionalManager.isPresent());
        Manager manager = optionalManager.get();
        assertEquals(124L, manager.getId());
        assertEquals(new BigDecimal("60001"), manager.getSalary());
        assertNull(manager.getEarningLess());
        assertNull(manager.getEarningMore());
        assertNull(manager.getByAmount());
    }

}