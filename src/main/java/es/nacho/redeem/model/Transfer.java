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

    @ManyToOne
    @JoinColumn(name = "emp_from_id", nullable = false)
    private Employee employeeFrom;

    @ManyToOne
    @JoinColumn(name = "emp_to_id", nullable = false)
    private Employee employeeTo;

    public Transfer(LocalDateTime datetime/*, Employee employee*/) {
        super();
        this.datetime = datetime;
//        this.employee = employee;
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

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
}
