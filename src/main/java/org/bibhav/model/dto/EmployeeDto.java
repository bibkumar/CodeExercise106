package org.bibhav.model.dto;

import org.bibhav.exception.BadRequestException;

import java.math.BigDecimal;
import java.util.List;

import static org.bibhav.util.AppConstants.INVALID_DATA_FORMAT_ERROR;

/**
 * Represents a Data Transfer Object (DTO) for Employee entities.
 * <p>
 * This class is designed to facilitate the transfer of employee data between different layers
 * of the application, such as the repository and service layers. It encapsulates essential
 * employee attributes, including their ID, name, salary, and manager ID.
 * <p>
 * Typical use cases include data validation, transformation, and ensuring consistency
 * when interacting with external systems or internal components.
 *
 * @author BibhavKumar
 */
public final class EmployeeDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final BigDecimal salary;
    private final Long managerId;

    /**
     * Constructs an EmployeeDto from a list of strings.
     *
     * @param line A list of strings representing employee data.
     *             Expected format: [id, firstName, lastName, salary, managerId (optional)]
     * @throws BadRequestException if the data format is invalid or parsing fails.
     */
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
            throw new BadRequestException(INVALID_DATA_FORMAT_ERROR);
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
