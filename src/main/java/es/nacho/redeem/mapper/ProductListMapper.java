package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductDto;
import java.util.ArrayList;
import java.util.Collection;

public interface ProductListMapper {
    static Collection<ProductDto> toProductListDto(Collection<Product> products){
        Collection<ProductDto> productDtos = new ArrayList<>();
        products.forEach(p -> {
            productDtos.add(ProductMapper.toProductDto(p));
        });
        return productDtos;
    }
}
