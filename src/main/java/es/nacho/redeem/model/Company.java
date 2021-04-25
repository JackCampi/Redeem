package es.nacho.redeem.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "company", uniqueConstraints = @UniqueConstraint(columnNames = "comp_name"))
public class Company {

    @Id
    @Column(name = "comp_id", nullable = false)
    private Long id;

    @Column(name = "comp_name", nullable = false)
    private String name;

    @Column(name = "comp_budget", nullable = false)
    private Long budget;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="company")
    private Collection<Product> products;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Area> areas;


    public Company(Long id, String name, Long budget, Collection<Product> products, Collection<Area> areas) {
        super();
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.products = products;
        this.areas = areas;
    }

    public Company(Long id, String name, Long budget, Collection<Area> areas) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.areas = areas;
    }

    public Company() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Collection<Area> getAreas() {
        return areas;
    }

    public void setAreas(Collection<Area> areas) {
        this.areas = areas;
    }
}
