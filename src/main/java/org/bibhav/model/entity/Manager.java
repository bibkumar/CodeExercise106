package org.bibhav.model.entity;


import java.math.BigDecimal;

/**
 * Represents a Manager entity within the organization.
 * <p>
 * This class encapsulates details about a manager, including their salary,
 * average salary of subordinates, and comparative earnings metrics. It provides
 * insights into whether the manager earns less or more than their subordinates
 * and by what amount.
 * <p>
 * Typical use cases include salary analysis, organizational hierarchy evaluation,
 * and reporting structure optimization.
 *
 * @author BibhavKumar
 */
public final class Manager {
    private final Long id;
    private final BigDecimal salary;
    private final BigDecimal avgSubOrdinatesSalary;
    private final Boolean earningLess;
    private final Boolean earningMore;
    private final BigDecimal byAmount;

    /**
     * Constructs a Manager instance with the specified attributes.
     *
     * @param id                    The unique identifier for the manager.
     * @param salary                The salary of the manager.
     * @param avgSubOrdinatesSalary The average salary of the manager's subordinates.
     * @param earningLess           Indicates if the manager earns less than their subordinates.
     * @param earningMore           Indicates if the manager earns more than their subordinates.
     * @param byAmount              The amount by which the manager's salary differs from their subordinates'.
     */
    public Manager(final Long id, final BigDecimal salary, final BigDecimal avgSubOrdinatesSalary, final Boolean earningLess, final Boolean earningMore, final BigDecimal byAmount) {
        this.id = id;
        this.salary = salary;
        this.avgSubOrdinatesSalary = avgSubOrdinatesSalary;
        this.earningLess = earningLess;
        this.earningMore = earningMore;
        this.byAmount = byAmount;
    }

    public Long getId() {
        return id;
    }


    public BigDecimal getSalary() {
        return salary;
    }


    public Boolean getEarningLess() {
        return earningLess;
    }


    public Boolean getEarningMore() {
        return earningMore;
    }


    public BigDecimal getByAmount() {
        return byAmount;
    }


    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", salary=" + salary +
                ", avgSubOrdinatesSalary=" + avgSubOrdinatesSalary +
                ", earningLess=" + earningLess +
                ", earningMore=" + earningMore +
                ", byAmount=" + byAmount +
                '}';
    }
}
