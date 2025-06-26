package org.example.model;

import lombok.Data;

@Data
public class Manager {
    private Long id;
    private Double salary;
    private Double avgSubOrdinatesSalary;
    private Boolean earningLess;
    private Boolean earningMore;
    private Double byAmount;
}
