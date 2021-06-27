package es.nacho.redeem.mapper;

import es.nacho.redeem.web.dto.report.EmployeeDto;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface EmployeeDtoMapper {

    static EmployeeDto toEmployeeDto(Object[] properties){

        BigDecimal amount = (BigDecimal) properties[1];
        BigDecimal purcasedAmount =(BigDecimal) properties[2];

        return new EmployeeDto(
                (String) properties[0],
                amount.longValue(),
                purcasedAmount.intValue()
        );
    }

}
