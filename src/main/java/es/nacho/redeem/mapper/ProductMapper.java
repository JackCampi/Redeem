package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;

import java.util.Objects;

public interface ProductMapper {
    static ProductDto toProductDto(Product product){
        return new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getType(),
                product.getStock(),
                product.getAvailable(),
                product.getImageUrl(),
                product.getDetails());
    }
    static Product toProduct(ProductWithDetailsDto product, Company company){
        return new Product(
                product.getName(),
                product.getPrice(),
                product.getType(),
                product.getStock(),
                product.getImageUrl(),
                product.getDetails(),
                company,
                true);
    }
    static Product toProductToUpdate(Product product, ProductWithDetailsDto productDto, Company company){
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setType(productDto.getType());
        product.setStock(productDto.getStock());
        product.setImageUrl(productDto.getImageUrl());
        product.setDetails(productDto.getDetails());
        return product;
    }
    static boolean validateProductWithDetailsDto(ProductWithDetailsDto product){
        return Objects.nonNull(product.getName()) &&
                Objects.nonNull(product.getStock()) &&
                Objects.nonNull(product.getImageUrl()) &&
                Objects.nonNull(product.getType()) &&
                Objects.nonNull(product.getDetails());
    }
}
