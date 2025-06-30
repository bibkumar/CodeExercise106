package org.bibhav.model;

import java.util.List;

/**
 * Employee DTO.
 *
 * @author BibhavKumar
 */
public class EmployeeDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Double salary;
    private final Long managerId;

    public EmployeeDto(final List<String> line) {
        this.id = Long.parseLong(line.get(0));
        this.firstName = line.get(1);
        this.lastName = line.get(2);
        this.salary = Double.parseDouble(line.get(3));
        if (line.size() > 4) { //Assumption 3 mentioned in readme file
            this.managerId = Long.parseLong(line.get(4));
        } else {
            this.managerId = null; //Null for CEO
        }
    }

    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public Double getSalary() {
        return salary;
    }


    public Long getManagerId() {
        return managerId;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", managerId=" + managerId +
                '}';
    }
}
