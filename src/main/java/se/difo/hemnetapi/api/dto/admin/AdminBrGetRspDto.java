package se.difo.hemnetapi.api.dto.admin;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.api.dto.common.CoordinateDto;

import java.math.BigDecimal;
import java.util.List;

public class AdminBrGetRspDto {

    public final String id;
    public final String address;
    public final String area;
    public final String url;
    public final Integer price;
    public final Integer fee;
    public final BigDecimal rooms;
    public final BigDecimal sqmLiving;

    public final String municipality;
    public final CoordinateDto coordinate;

    public final BrokerDto broker;

    public final String timeAdded;
    public final String timeUpdated;
    public final String timeRemoved;

    public final List<String> imageUrls;

    public final String brfId;

    public AdminBrGetRspDto(
            String id,
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
            String timeAdded,
            String timeUpdated,
            String timeRemoved,
            List<String> imageUrls,
            String brfId
    ) {
        this.id = id;
        this.address = address;
        this.area = area;
        this.url = url;
        this.price = price;
        this.fee = fee;
        this.rooms = rooms;
        this.sqmLiving = sqmLiving;
        this.municipality = municipality;
        this.coordinate = coordinate;
        this.broker = broker;
        this.timeAdded = timeAdded;
        this.timeUpdated = timeUpdated;
        this.timeRemoved = timeRemoved;
        this.imageUrls = imageUrls;
        this.brfId = brfId;
    }

}
