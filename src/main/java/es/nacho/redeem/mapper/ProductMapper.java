package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;

public interface ProductMapper {
    static ProductDto toProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl());
    }
    static Product toProduct(ProductDto productDto, ProductDetailsDto productDetailsDto){
        return new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDetailsDto.getType(),
                productDto.getStock(),
                productDto.getImageUrl()); //TODO add productDetailsDto.setDetails
    }
}
