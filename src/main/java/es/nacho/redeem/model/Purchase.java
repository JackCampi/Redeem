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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "purchase")
    private Collection<PurchaseHasProduct> purchaseHasProducts;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    public Purchase(Calendar dateTime) {
        super();
        this.dateTime = dateTime;
    }

    public Purchase() {

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
}
