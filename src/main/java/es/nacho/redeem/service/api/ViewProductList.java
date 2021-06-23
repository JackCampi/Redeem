package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.ProductDto;
import java.util.Collection;

public interface ViewProductList {
    Collection<ProductDto> get(long companyNIT) throws Exception;
}
