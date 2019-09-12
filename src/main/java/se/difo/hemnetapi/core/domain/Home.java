package se.difo.hemnetapi.core.domain;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Home {

    protected static final String IMAGE_LOCATION_PREFIX = "/images/";

    protected UUID id;
    protected String address;
    protected String area;
    protected String url;
    protected Integer price;
    protected BigDecimal sqmLiving;
    protected String municipality;
    protected Coordinate coordinate;
    protected BigDecimal rooms;

    protected Broker broker;

    protected Instant timeRemoved;
    protected Instant timeAdded;
    protected Instant timeUpdated;

    protected List<Image> images;

    protected Integer visitCount;


    Home(UUID id, String address, String area, String url, Integer price, BigDecimal sqmLiving, String municipality, Coordinate coordinate, BigDecimal rooms, Broker broker, Instant timeAdded, Instant timeUpdated, Instant timeRemoved, List<Image> images, Integer visitCount) {
        this.id = id;
        this.address = address;
        this.area = area;
        this.url = url;
        this.price = price;
        this.sqmLiving = sqmLiving;
        this.municipality = municipality;
        this.coordinate = coordinate;
        this.rooms = rooms;
        this.broker = broker;
        this.timeRemoved = timeRemoved;
        this.timeAdded = timeAdded;
        this.timeUpdated = timeUpdated;
        this.images = images;
        this.visitCount = visitCount;
    }

    public UUID getId() {
        return id;
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

    public Integer getPrice() {
        return price;
    }

    public BigDecimal getSqmLiving() {
        return sqmLiving;
    }

    public String getMunicipality() {
        return municipality;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public BigDecimal getRooms() {
        return rooms;
    }

    public Broker getBroker() {
        return broker;
    }

    public Instant getTimeRemoved() {
        return timeRemoved;
    }

    public Instant getTimeAdded() {
        return timeAdded;
    }

    public Instant getTimeUpdated() {
        return timeUpdated;
    }

    public List<Image> getImages() {
        return images;
    }

    public boolean isRemoved() {
        return getTimeRemoved() != null;
    }

    public String getFrontImagePath() {
        return images.isEmpty() ? null : IMAGE_LOCATION_PREFIX + images.get(0).getPath();
    }

    public List<String> getImagePaths() {
        return images.stream()
                .map(Image::getPath)
                .map(path -> IMAGE_LOCATION_PREFIX + path)
                .collect(Collectors.toList());
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setAddressIfNotNull(String address) {
        if (address == null) {
            return;
        }
        this.address = address;
    }

    public void setAreaIfNotNull(String area) {
        if (area == null) {
            return;
        }
        this.area = area;
    }

    public void setPriceIfNotNull(Integer price) {
        if (price == null) {
            return;
        }
        this.price = price;
    }

    public void setSqmLivingIfNotNull(BigDecimal sqmLiving) {
        if (sqmLiving == null) {
            return;
        }
        this.sqmLiving = sqmLiving;
    }

    public void setMunicipalityIfNotNull(String municipality) {
        if (municipality == null) {
            return;
        }
        this.municipality = municipality;
    }

    public void setCoordinateIfNotNull(Coordinate coordinate) {
        if (coordinate == null) {
            return;
        }
        this.coordinate = coordinate;
    }

    public void setRoomsIfNotNull(BigDecimal rooms) {
        if (rooms == null) {
            return;
        }
        this.rooms = rooms;
    }

    public void setBrokerIfNotNull(Broker broker) {
        if (broker == null) {
            return;
        }
        this.broker = broker;
    }

    public void setTimeAddedIfNotNull(Instant timeAdded) {
        if (timeAdded == null) {
            return;
        }
        this.timeAdded = timeAdded;
    }

    public void setTimeUpdatedIfNotNull(Instant timeUpdated) {
        if (timeUpdated == null) {
            return;
        }
        this.timeUpdated = timeUpdated;
    }

    public void setVisitCountIfNotNull(Integer visitCount) {
        if (visitCount == null) {
            return;
        }
        this.visitCount = visitCount;
    }



    public void setTimeRemoved(Instant timeRemoved) {
        this.timeRemoved = timeRemoved;
    }

}
