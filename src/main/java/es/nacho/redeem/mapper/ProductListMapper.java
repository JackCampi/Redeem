package es.nacho.redeem.mapper;

import es.nacho.redeem.model.Product;
import es.nacho.redeem.web.dto.ProductDto;
import java.util.ArrayList;
import java.util.Collection;

public interface ProductListMapper {
    static Collection<ProductDto> toProductListDto(Collection<Product> products){
        Collection<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            if(product.getAvailable())
                productDtos.add(ProductMapper.toProductDto(product));
        });
        return productDtos;
    }
}
