package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.ProductDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;

public interface AddProductService {
    void invoke(ProductDto productDto, ProductDetailsDto productDetailsDto, Long companyNIT) throws Exception;
}
