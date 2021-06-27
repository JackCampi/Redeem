package es.nacho.redeem.mapper;

import es.nacho.redeem.web.dto.report.CategoryDto;

import java.math.BigDecimal;

public interface CategoryDtoMapper {

    static CategoryDto toCategoryDto(Object[] properties){

        BigDecimal amount = (BigDecimal) properties[1];
        return new CategoryDto(
                (String) properties[0],
                amount.intValue()
        );
    }

}
