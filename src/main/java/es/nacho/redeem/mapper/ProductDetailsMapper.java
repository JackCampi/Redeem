package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductDetailsDto;

public interface ProductDetailsMapper {
    static ProductDetailsDto toProductDetailsDto(Product product){
        return new ProductDetailsDto(
                product.getType(),
                ""); //TODO
    }
}
