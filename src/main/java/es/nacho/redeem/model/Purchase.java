package es.nacho.redeem.model;

import javax.persistence.*;
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
    private Calendar dateTime;

    @Column(name = "pur_sent", nullable = false)
    private Boolean isSent;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "purchase")
    private Collection<PurchaseHasProduct> purchaseHasProducts;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    public Purchase(Calendar dateTime) {
        super();
        this.dateTime = dateTime;
        this.isSent = false;
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

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(Boolean isSent) {
        this.isSent = isSent;
    }

}
