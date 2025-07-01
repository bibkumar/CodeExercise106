package org.bibhav.model.entity;


import java.math.BigDecimal;

/**
 * Manager POJO.
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
