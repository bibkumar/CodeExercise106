package org.bibhav.model.dto;

import org.bibhav.exception.BadRequestException;

import java.math.BigDecimal;
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
    private final BigDecimal salary;
    private final Long managerId;

    public EmployeeDto(final List<String> line) throws BadRequestException {
        try {
            this.id = Long.parseLong(line.get(0));
            this.firstName = line.get(1);
            this.lastName = line.get(2);
            this.salary = new BigDecimal(line.get(3));
            if (line.size() > 4) {
                this.managerId = Long.parseLong(line.get(4));
            } else {
                this.managerId = null;
            }
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid data format in processing file");
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


    public BigDecimal getSalary() {
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
