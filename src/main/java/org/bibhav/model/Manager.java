package org.bibhav.model;


/**
 * Manager POJO.
 *
 * @author BibhavKumar
 */
public class Manager {
    private Long id;
    private Double salary;
    private Double avgSubOrdinatesSalary;
    private Boolean earningLess;
    private Boolean earningMore;
    private Double byAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public void setAvgSubOrdinatesSalary(Double avgSubOrdinatesSalary) {
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

    public Double getByAmount() {
        return byAmount;
    }

    public void setByAmount(Double byAmount) {
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
