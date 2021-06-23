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
}
