package es.nacho.redeem.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Calendar;

@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = "emp_email"))
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    @Column(name = "emp_last_name", nullable = false)
    private String lastName;

    @Column(name = "emp_email", nullable = false)
    private String email;

    @Column(name = "emp_password", nullable = false)
    private String password;

    @Column(name = "emp_cel", nullable = false)
    private String cellphone;

    @Column(name = "emp_birthday", nullable = false)
    private Calendar birthday;

    @Column(name = "emp_balance", nullable = false)
    private Long balance;

    @Column(name = "emp_active", nullable = false)
    private Boolean active;

    @Column(name = "emp_rol", nullable = false)
    private String rol;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<Purchase> purchases;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "area_area_id", referencedColumnName = "area_name", nullable = false),
            @JoinColumn(name = "area_company_comp_id", referencedColumnName = "company_comp_id", nullable = false)
    })
    private Area area;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employeeFrom")
    private Collection<Transfer> outgoingTransfers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employeeTo")
    private Collection<Transfer> incomingTransfers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "admin")
    private Collection<Allocation> outgoinAllocations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<Allocation> incomingAllocations;

    public Employee(String name, String lastName, String email, String password, String cellphone, Calendar birthday, Long balance, Boolean active, String rol, Area area/*, Collection<Purchase> purchases, Collection<Transfer> transfers/*, Collection<Allocation> allocations*/) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.birthday = birthday;
        this.balance = balance;
        this.active = active;
        this.rol = rol;
        this.area = area;
    }

    public Employee() {

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Collection<Transfer> getOutgoingTransfers() {
        return outgoingTransfers;
    }

    public void setOutgoingTransfers(Collection<Transfer> outgoingTransfers) {
        this.outgoingTransfers = outgoingTransfers;
    }

    public Collection<Transfer> getIncomingTransfers() {
        return incomingTransfers;
    }

    public void setIncomingTransfers(Collection<Transfer> incomingTransfers) {
        this.incomingTransfers = incomingTransfers;
    }

    public Collection<Allocation> getOutgoinAllocations() {
        return outgoinAllocations;
    }

    public void setOutgoinAllocations(Collection<Allocation> outgoinAllocations) {
        this.outgoinAllocations = outgoinAllocations;
    }

    public Collection<Allocation> getIncomingAllocations() {
        return incomingAllocations;
    }

    public void setIncomingAllocations(Collection<Allocation> incomingAllocations) {
        this.incomingAllocations= incomingAllocations;
    }

    public Collection<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }
}
