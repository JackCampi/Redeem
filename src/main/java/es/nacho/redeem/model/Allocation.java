package es.nacho.redeem.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

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
    private Integer amount;

    @Column(name="al_description", nullable = false, columnDefinition = "TEXt")
    private String description;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "area_area_id",referencedColumnName = "area_id", nullable = false),
            @JoinColumn(name = "area_company_comp_id",referencedColumnName = "company_comp_id", nullable = false)
    })
    private Area area;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    public Allocation(LocalDateTime datetime, Integer amount, String description) {
        super();
        this.datetime = datetime;
        this.amount = amount;
        this.description = description;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
