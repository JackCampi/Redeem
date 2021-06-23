package es.nacho.redeem.web.dto;

import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String price;
    private Long stock;
    private String imageUrl;
}
