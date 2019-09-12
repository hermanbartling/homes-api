package se.difo.hemnetapi.api.dto.admin;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.api.dto.common.CoordinateDto;

import java.math.BigDecimal;

public class AdminBrCreateReqDto extends AdminBrUpdateReqDto {

    public final String url;

    public AdminBrCreateReqDto(
            String address,
            String area,
            String url,
            Integer price,
            Integer fee,
            BigDecimal rooms,
            BigDecimal sqmLiving,
            String municipality,
            CoordinateDto coordinate,
            BrokerDto broker,
            String timeRemoved,
            String timeAdded,
            String timeUpodated,
            Integer visitCount,
            String brfId
    ) {
        super(
                address,
                area,
                price,
                fee,
                rooms,
                sqmLiving,
                municipality,
                coordinate,
                broker,
                timeRemoved,
                timeAdded,
                timeUpodated,
                visitCount,
                brfId
        );
        this.url = url;
    }

}
