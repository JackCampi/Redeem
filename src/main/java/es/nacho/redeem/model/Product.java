package es.nacho.redeem.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "product")
@Getter @Setter
@NoArgsConstructor
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

    @Column(name = "prod_image_url", nullable = false, columnDefinition = "TEXT", length = 10000)
    private String imageUrl;

    @Column(name = "prod_details", nullable = false, columnDefinition = "TEXT")
    private String details;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<PurchaseHasProduct> purchaseHasProducts;

    @ManyToOne
    @JoinColumn(name = "company_comp_id", nullable = false)
    private Company company;

    public Product(String name, Long price, String type, Integer stock, String imageUrl, String details, Company company, Boolean available) {
        super();
        this.name = name;
        this.available = available;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.details = details;
        this.company = company;
    }

    public Product(Long id, String name, Long price, String type, Integer stock, String imageUrl, String details, Company company) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.details = details;
        this.company = company;
    }
}
