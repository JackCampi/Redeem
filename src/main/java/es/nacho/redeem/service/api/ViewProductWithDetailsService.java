package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.ProductWithDetailsDto;

public interface ViewProductWithDetailsService {
    ProductWithDetailsDto get(Long id) throws Exception;
}
