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
    private String price;

    @Column(name = "prod_type", nullable = false)
    private String type;

    @Column(name = "prod_stock", nullable = false)
    private Long stock;

    @Column(name = "prod_image_url", nullable = false)
    private String imageUrl;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<PurchaseHasProduct> purchaseHasProducts;

    @ManyToOne
    @JoinColumn(name = "comp_id", nullable = false)
    private Company company;

    public Product(String name, String price, String type, Long stock, String imageUrl) {
        super();
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.imageUrl = imageUrl;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
