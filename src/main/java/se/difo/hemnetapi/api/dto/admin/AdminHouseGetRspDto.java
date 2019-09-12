package se.difo.hemnetapi.api.dto.admin;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.api.dto.common.CoordinateDto;

import java.math.BigDecimal;
import java.util.List;

public class AdminHouseGetRspDto {

    public String id;
    public String address;
    public String area;
    public String url;
    public String type;
    public Integer price;
    public Integer costYear;
    public Integer builtYear;
    public BigDecimal rooms;
    public BigDecimal sqmLiving;
    public BigDecimal sqmLand;
    public BigDecimal sqmLivingAdditional;

    public String municipality;
    public CoordinateDto coordinate;

    public BrokerDto broker;

    public String timeRemoved;
    public String timeAdded;
    public String timeUpdated;

    public List<String> imageUrls;

    public AdminHouseGetRspDto() {

    }

    public AdminHouseGetRspDto(
            String id,
            String address,
            String area,
            String url,
            String type,
            Integer price,
            Integer costYear,
            Integer builtYear,
            BigDecimal rooms,
            BigDecimal sqmLiving,
            BigDecimal sqmLand,
            BigDecimal sqmLivingAdditional,
            String municipality,
            CoordinateDto coordinate,
            BrokerDto broker,
            String timeRemoved,
            String timeAdded,
            String timeUpdated,
            List<String> imageUrls
    ) {
        this.id = id;
        this.address = address;
        this.area = area;
        this.url = url;
        this.type = type;
        this.price = price;
        this.costYear = costYear;
        this.builtYear = builtYear;
        this.rooms = rooms;
        this.sqmLiving = sqmLiving;
        this.sqmLand = sqmLand;
        this.sqmLivingAdditional = sqmLivingAdditional;
        this.municipality = municipality;
        this.coordinate = coordinate;
        this.broker = broker;
        this.timeRemoved = timeRemoved;
        this.timeAdded = timeAdded;
        this.timeUpdated = timeUpdated;
        this.imageUrls = imageUrls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCostYear() {
        return costYear;
    }

    public void setCostYear(Integer costYear) {
        this.costYear = costYear;
    }

    public Integer getBuiltYear() {
        return builtYear;
    }

    public void setBuiltYear(Integer builtYear) {
        this.builtYear = builtYear;
    }

    public BigDecimal getRooms() {
        return rooms;
    }

    public void setRooms(BigDecimal rooms) {
        this.rooms = rooms;
    }

    public BigDecimal getSqmLiving() {
        return sqmLiving;
    }

    public void setSqmLiving(BigDecimal sqmLiving) {
        this.sqmLiving = sqmLiving;
    }

    public BigDecimal getSqmLand() {
        return sqmLand;
    }

    public void setSqmLand(BigDecimal sqmLand) {
        this.sqmLand = sqmLand;
    }

    public BigDecimal getSqmLivingAdditional() {
        return sqmLivingAdditional;
    }

    public void setSqmLivingAdditional(BigDecimal sqmLivingAdditional) {
        this.sqmLivingAdditional = sqmLivingAdditional;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public CoordinateDto getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateDto coordinate) {
        this.coordinate = coordinate;
    }

    public String getTimeRemoved() {
        return timeRemoved;
    }

    public void setTimeRemoved(String timeRemoved) {
        this.timeRemoved = timeRemoved;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public BrokerDto getBroker() {
        return broker;
    }

    public void setBroker(BrokerDto broker) {
        this.broker = broker;
    }
}
