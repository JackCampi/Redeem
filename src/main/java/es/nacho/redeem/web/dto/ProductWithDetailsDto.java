package es.nacho.redeem.web.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithDetailsDto {
    private Long id;
    private String name;
    private String price;
    private Long stock;
    private String imageUrl;
    private String type;
    private String details;
}
