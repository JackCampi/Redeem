package es.nacho.redeem.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id", nullable = false)
    private Long id;

    @Column(name = "prod_name", nullable = false)
    private String name;

    @Column(name = "prod_price", nullable = false)
    private Long price;

    @Column(name = "prod_type", nullable = false)
    private String type;

    @Column(name = "prod_stock", nullable = false)
    private Integer stock;

    @Column(name = "prod_available", nullable = false)
    private Boolean available;

    @Column(name = "prod_image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<PurchaseHasProduct> purchaseHasProducts;

    @ManyToOne
    @JoinColumn(name = "company_comp_id", nullable = false)
    private Company company;

    public Product(String name, Long price, String type, Integer stock, Boolean available, String imageUrl, Company company) {
        super();
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.available = available;
        this.imageUrl = imageUrl;
        this.company = company;
    }

    public Product() {

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
