package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductDetailsDto;

public interface ProductDetailsMapper {
    default ProductDetailsDto toProductDetailsDto(Product product){
        return new ProductDetailsDto(
                product.getType(),
                ""); //TODO
    }
}
