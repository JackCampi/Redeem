package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.ProductWithDetailsDto;

import java.util.Collection;

public interface ViewProductWithDetailsService {
    ProductWithDetailsDto getProduct(Long id) throws Exception;
    Collection<ProductWithDetailsDto> getList(Long companyNIT) throws Exception;
}
