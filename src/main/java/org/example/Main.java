package org.example;

import org.example.model.Employee;
import org.example.model.EmployeeDto;
import org.example.model.Manager;
import org.example.repository.FileEmployeeRepository;
import org.example.service.EmployeeService;
import org.example.service.ManagerSalaryComparisonService;
import org.example.service.ReportService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository("src/main/resources/data.csv");
        EmployeeService employeeService = new EmployeeService(fileEmployeeRepository);
        ManagerSalaryComparisonService managerSalaryComparisonService = new ManagerSalaryComparisonService();
        Set<Employee> employees = employeeService.fetchAllEmployees();
        Set<Manager> managers = managerSalaryComparisonService.fetchManagersWithSalaryComparison(employees);
        System.out.println(managers);
        ReportService reportService = new ReportService(managerSalaryComparisonService);
        Map<Long, Double> underpaidManagersWithDisparity = reportService.getUnderpaidManagersWithDisparity(employees);
        System.out.println(underpaidManagersWithDisparity);
        Map<Long, Double> overpaidManagersWithDisparity = reportService.getOverpaidManagersWithDisparity(employees);
        System.out.println(overpaidManagersWithDisparity);
    }

}