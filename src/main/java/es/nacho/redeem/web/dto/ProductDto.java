package es.nacho.redeem.web.dto;

public class ProductDto {
    private String name;
    private Long price;
    private String type;
    private Integer stock;
    private Boolean available;
    private String imageUrl;
    private String description;

    public ProductDto(String name, Long price, String type, Integer stock, Boolean available, String imageUrl, String description) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.available = available;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public ProductDto() {
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
