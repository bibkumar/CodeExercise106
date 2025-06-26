package org.bibhav.model;

import lombok.Data;

import java.util.List;

/**
 * Employee DTO.
 *
 * @author BibhavKumar
 */
@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Double salary;
    private Long managerId;

    public EmployeeDto(List<String> line) {
        this.id = Long.parseLong(line.get(0));
        this.firstName = line.get(1);
        this.lastName = line.get(2);
        this.salary = Double.parseDouble(line.get(3));
        if (line.size() > 4) { // few records has empty manager Id
            this.managerId = Long.parseLong(line.get(4));
        }
    }

}
