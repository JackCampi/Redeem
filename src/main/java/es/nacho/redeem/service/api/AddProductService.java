package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.ProductWithDetailsDto;

public interface AddProductService {
    void invoke(ProductWithDetailsDto productWithDetailsDto, Long companyNIT) throws Exception;
}
