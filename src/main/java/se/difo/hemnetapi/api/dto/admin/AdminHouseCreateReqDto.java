package se.difo.hemnetapi.api.dto.admin;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.api.dto.common.CoordinateDto;

import java.math.BigDecimal;

public class AdminHouseCreateReqDto {

    private String address;
    private String area;
    private String url;
    private String type;
    private Integer price;
    private BigDecimal rooms;


    private BigDecimal sqmLiving;
    private BigDecimal sqmLand;
    private BigDecimal sqmLivingAdditional;

    private CoordinateDto coordinate;
    private String municipality;
    private BrokerDto broker;

    private Integer costYear;
    private Integer builtYear;

    private String timeRemoved;
    private String timeAdded;
    private String timeUpdated;

    private Integer visitCount;

    public AdminHouseCreateReqDto() {
    }

    public AdminHouseCreateReqDto(
            String address,
            String area,
            String url,
            String type,
            Integer price,
            BigDecimal rooms,
            BrokerDto broker,
            BigDecimal sqmLiving,
            BigDecimal sqmLand,
            BigDecimal sqmLivingAdditional,
            String municipality,
            CoordinateDto coordinate,
            Integer costYear,
            Integer builtYear,
            String timeRemoved,
            String timeAdded,
            String timeUpdated,
            Integer visitCount
    ) {
        this.address = address;
        this.area = area;
        this.url = url;
        this.type = type;
        this.price = price;
        this.rooms = rooms;
        this.broker = broker;
        this.sqmLiving = sqmLiving;
        this.sqmLand = sqmLand;
        this.sqmLivingAdditional = sqmLivingAdditional;
        this.municipality = municipality;
        this.coordinate = coordinate;
        this.costYear = costYear;
        this.builtYear = builtYear;
        this.timeRemoved = timeRemoved;
        this.timeAdded = timeAdded;
        this.timeUpdated = timeUpdated;
        this.visitCount = visitCount;
    }

    public String getAddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }

    public BigDecimal getRooms() {
        return rooms;
    }

    public BigDecimal getSqmLiving() {
        return sqmLiving;
    }

    public BigDecimal getSqmLand() {
        return sqmLand;
    }

    public BigDecimal getSqmLivingAdditional() {
        return sqmLivingAdditional;
    }

    public String getMunicipality() {
        return municipality;
    }

    public Integer getCostYear() {
        return costYear;
    }

    public Integer getBuiltYear() {
        return builtYear;
    }

    public String getTimeRemoved() {
        return timeRemoved;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public CoordinateDto getCoordinate() {
        return coordinate;
    }

    public BrokerDto getBroker() {
        return broker;
    }

    public Integer getVisitCount() {
        return visitCount;
    }
}
