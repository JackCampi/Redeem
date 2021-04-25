package es.nacho.redeem.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    @Column(name="area_name")
    private String name;

    @ManyToOne
    @MapsId("comp_id")
    @JoinColumn(name = "company_comp_id", nullable = false)
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "area")
    private Collection<Employee> employees;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "area")
    private Collection<Allocation> allocations;

    public Area(String name, Collection<Employee> employees, Collection<Allocation> allocations) {
        super();
        this.name = name;
//        this.employees = employees;
//        this.allocations = allocations;
    }

    public Area() {
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

//    public Collection<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(Collection<Employee> employees) {
//        this.employees = employees;
//    }
//
//    public Collection<Allocation> getAllocations() {
//        return allocations;
//    }
//
//    public void setAllocations(Collection<Allocation> allocations) {
//        this.allocations = allocations;
//    }
}
