package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;

public interface ProductDetailsMapper {
    static ProductWithDetailsDto toProductDetailsDto(Product product){
        return new ProductWithDetailsDto(
                product.getId(), 
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.getType(),
                product.getDetails());
    }
}
