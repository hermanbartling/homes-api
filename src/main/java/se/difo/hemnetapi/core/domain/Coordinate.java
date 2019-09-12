package se.difo.hemnetapi.core.domain;


import java.math.BigDecimal;

public class Coordinate {

    private BigDecimal lat;
    private BigDecimal lng;

    public Coordinate(BigDecimal lat, BigDecimal lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLatIfNotNull(BigDecimal lat) {
        if(lat == null) {
            return;
        }
        this.lat = lat;
    }

    public void setLngIfNotNull(BigDecimal lng) {
        if(lng == null) {
            return;
        }
        this.lng = lng;
    }
}
