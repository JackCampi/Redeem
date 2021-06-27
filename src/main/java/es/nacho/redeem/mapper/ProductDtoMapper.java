package es.nacho.redeem.mapper;

import es.nacho.redeem.web.dto.report.ProductDto;

import java.math.BigDecimal;

public interface ProductDtoMapper {

    static ProductDto toProductDto(Object[] properties){

        BigDecimal sales = (BigDecimal) properties[2];

        return new ProductDto(
                (String) properties[0],
                (String) properties[1],
                sales.intValue()
        );
    }

}
