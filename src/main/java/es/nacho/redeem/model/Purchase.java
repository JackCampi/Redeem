package es.nacho.redeem.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pur_id", nullable = false)
    private Long id;

    @Column(name = "pur_datetime", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "pur_sent", nullable = false)
    private Boolean isSent;

    @Column(name = "pur_value", nullable = false)
    private Long value;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "purchase")
    private Collection<PurchaseHasProduct> purchaseHasProducts;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    public Purchase(LocalDateTime dateTime, Employee employee, Long value) {
        super();
        this.dateTime = dateTime;
        this.isSent = false;
        this.employee = employee;
        this.value = value;
    }

    public Purchase() {

    }

    public Collection<PurchaseHasProduct> getPurchaseHasProducts() {
        return purchaseHasProducts;
    }

    public void setPurchaseHasProducts(Collection<PurchaseHasProduct> purchaseHasProducts) {
        this.purchaseHasProducts = purchaseHasProducts;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(Boolean isSent) {
        this.isSent = isSent;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
