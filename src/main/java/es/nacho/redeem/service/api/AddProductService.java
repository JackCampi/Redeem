package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;

public interface AddProductService {
    void invoke(ProductWithDetailsDto productWithDetailsDto, Long companyNIT) throws Exception;
}
