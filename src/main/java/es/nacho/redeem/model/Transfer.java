package es.nacho.redeem.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_id", nullable = false)
    private Long id;

    @Column(name = "tr_datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name="amount", nullable = false)
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "emp_from_id", nullable = false)
    private Employee employeeFrom;

    @ManyToOne
    @JoinColumn(name = "emp_to_id", nullable = false)
    private Employee employeeTo;

    public Transfer(LocalDateTime datetime, Employee employeeFrom, Employee employeeTo, long amount) {
        super();
        this.datetime = datetime;
        this.employeeFrom = employeeFrom;
        this.employeeTo = employeeTo;
        this.amount = amount;
    }

    public Transfer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Employee getEmployeeFrom() {
        return employeeFrom;
    }

    public void setEmployeeFrom(Employee employeeFrom) {
        this.employeeFrom = employeeFrom;
    }

    public Employee getEmployeeTo() {
        return employeeTo;
    }

    public void setEmployeeTo(Employee employeeTo) {
        this.employeeTo = employeeTo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
