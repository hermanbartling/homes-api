package se.difo.hemnetapi.api.dto.admin;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.api.dto.common.CoordinateDto;

import java.math.BigDecimal;

public class AdminBrUpdateReqDto {

    public final String address;
    public final String area;
    public final Integer price;
    public final Integer fee;
    public final BigDecimal rooms;
    public final BigDecimal sqmLiving;
    public final String municipality;
    public final CoordinateDto coordinate;
    public final BrokerDto broker;
    public final String timeRemoved;
    public final String timeAdded;
    public final String timeUpdated;
    public final Integer visitCount;
    public final String brfId;


    public AdminBrUpdateReqDto(
            String address,
            String area,
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
        this.address = address;
        this.area = area;
        this.price = price;
        this.fee = fee;
        this.rooms = rooms;
        this.sqmLiving = sqmLiving;
        this.municipality = municipality;
        this.coordinate = coordinate;
        this.broker = broker;
        this.timeRemoved = timeRemoved;
        this.timeAdded = timeAdded;
        this.timeUpdated = timeUpodated;
        this.visitCount = visitCount;
        this.brfId = brfId;
    }
}
