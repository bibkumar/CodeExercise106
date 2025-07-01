package org.bibhav.model.entity;


import java.math.BigDecimal;

/**
 * Manager POJO.
 *
 * @author BibhavKumar
 */
public class Manager {
    private Long id;
    private BigDecimal salary;
    private BigDecimal avgSubOrdinatesSalary;
    private Boolean earningLess;
    private Boolean earningMore;
    private BigDecimal byAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }


    public void setAvgSubOrdinatesSalary(BigDecimal avgSubOrdinatesSalary) {
        this.avgSubOrdinatesSalary = avgSubOrdinatesSalary;
    }

    public Boolean getEarningLess() {
        return earningLess;
    }

    public void setEarningLess(Boolean earningLess) {
        this.earningLess = earningLess;
    }

    public Boolean getEarningMore() {
        return earningMore;
    }

    public void setEarningMore(Boolean earningMore) {
        this.earningMore = earningMore;
    }

    public BigDecimal getByAmount() {
        return byAmount;
    }

    public void setByAmount(BigDecimal byAmount) {
        this.byAmount = byAmount;
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
