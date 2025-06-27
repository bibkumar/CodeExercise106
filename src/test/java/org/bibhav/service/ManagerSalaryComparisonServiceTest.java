package org.bibhav.service;

import org.bibhav.TestUtility;
import org.bibhav.model.Employee;
import org.bibhav.model.Manager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(45000D);

        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);

        assertEquals(1, managers.size());
        Optional<Manager> optionalManager = managers.stream().findFirst();
        assertTrue(optionalManager.isPresent());
        Manager manager = optionalManager.get();
        assertEquals(124L, manager.getId());
        assertEquals(45000D, manager.getSalary());
        assertTrue(manager.getEarningLess());
        assertNull(manager.getEarningMore());
        assertEquals(50000 * 1.2 - 45000, manager.getByAmount());
    }


    @Test
    void fetchManagersWithSalaryComparison_ManagerEarningMore() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(76000D);

        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);

        assertEquals(1, managers.size());
        Optional<Manager> optionalManager = managers.stream().findFirst();
        assertTrue(optionalManager.isPresent());
        Manager manager = optionalManager.get();
        assertEquals(124L, manager.getId());
        assertEquals(76000D, manager.getSalary());
        assertTrue(manager.getEarningMore());
        assertNull(manager.getEarningLess());
        assertEquals(76000 - 50000 * 1.5, manager.getByAmount());
    }

    @Test
    void fetchManagersWithSalaryComparison_ManagerEarningNormal() {
        Set<Employee> employees = TestUtility.getEmployeesWithSubOrdinatesSalaryConfigurable(60001D);

        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);

        assertEquals(1, managers.size());
        Optional<Manager> optionalManager = managers.stream().findFirst();
        assertTrue(optionalManager.isPresent());
        Manager manager = optionalManager.get();
        assertEquals(124L, manager.getId());
        assertEquals(60001D, manager.getSalary());
        assertNull(manager.getEarningLess());
        assertNull(manager.getEarningMore());
        assertNull(manager.getByAmount());
    }

}