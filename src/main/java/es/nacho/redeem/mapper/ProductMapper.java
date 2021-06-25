package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;

import java.util.Objects;

public interface ProductMapper {
    static ProductDto toProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl());
    }
    static Product toProduct(ProductWithDetailsDto product, Company company){
        return new Product(
                product.getName(),
                product.getPrice(),
                product.getType(),
                product.getStock(),
                product.getImageUrl(),
                product.getDetails(),
                company);
    }
    static boolean validateProductWithDetailsDto(ProductWithDetailsDto product){
        return Objects.nonNull(product.getId()) &&
                Objects.nonNull(product.getName()) &&
                Objects.nonNull(product.getStock()) &&
                Objects.nonNull(product.getImageUrl()) &&
                Objects.nonNull(product.getType()) &&
                Objects.nonNull(product.getDetails());
    }
}
