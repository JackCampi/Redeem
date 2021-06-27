package es.nacho.redeem.mapper;

import es.nacho.redeem.web.dto.report.PendingShipmentsDto;

import java.math.BigInteger;

public interface PendingShipmentDtoMapper {

    static PendingShipmentsDto toPendingShipmentDto(Object[] properties){

        BigInteger id = (BigInteger) properties[1];
        BigInteger cost = (BigInteger) properties[2];

        return new PendingShipmentsDto(
                (String) properties[0],
                id.longValue(),
                cost.longValue()
        );
    }

}
