package es.nacho.redeem.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="allocation")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "al_id")
    private Long id;

    @Column(name="al_datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name="al_amount", nullable = false)
    private Long amount;

    @Column(name="al_description", nullable = false, columnDefinition = "TEXt")
    private String description;

    @ManyToOne
    @JoinColumn(name = "adm_id", nullable = false)
    private Employee admin;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    

    public Allocation(LocalDateTime datetime, Long amount, String description, Employee admin, Employee employee) {
        super();
        this.datetime = datetime;
        this.amount = amount;
        this.description = description;
        this.admin = admin;
        this.employee = employee;
    }

    public Allocation() {

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getAdmin() {
        return admin;
    }

    public void setAdmin(Employee admin) {
        this.admin = admin;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
}
