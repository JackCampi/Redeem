package es.nacho.redeem.web.dto;

import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Long price;
    private Integer stock;
    private String imageUrl;
}
