package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.ProductDetailsDto;

public interface ViewProductDetailsService {
    ProductDetailsDto get(Long id) throws Exception;
}
