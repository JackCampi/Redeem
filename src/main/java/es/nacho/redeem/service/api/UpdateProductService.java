package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.ProductWithDetailsDto;

public interface UpdateProductService {
    void invoke(ProductWithDetailsDto productWithDetailsDto, Long companyNIT) throws Exception;
}
