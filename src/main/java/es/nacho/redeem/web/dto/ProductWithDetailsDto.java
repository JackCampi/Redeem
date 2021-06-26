package es.nacho.redeem.web.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithDetailsDto {
    private Long id;
    private String name;
    private Long price;
    private Integer stock;
    private String imageUrl;
    private String type;
    private String details;

    public ProductWithDetailsDto(String name, Long price, Integer stock, String imageUrl, String type, String details) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.type = type;
        this.details = details;
    }
}
