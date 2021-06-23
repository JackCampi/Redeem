package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;

public interface ProductDetailsMapper {
    static ProductWithDetailsDto toProductDetailsDto(Product product){
        return new ProductWithDetailsDto(
                product.getId(),
                product.getName(),
                Long.getLong(product.getPrice()), //TODO delete Long cast
                Integer.getInteger(product.getStock().toString()), //TODO delete String Integer cast
                product.getImageUrl(),
                product.getType(),
                ""); //TODO product.getDetails()
    }
}
