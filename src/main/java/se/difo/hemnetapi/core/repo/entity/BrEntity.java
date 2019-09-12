package se.difo.hemnetapi.core.repo.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "br")
@Table(name = "br")
@EntityListeners(AuditingEntityListener.class)
public class BrEntity {

    // https://phauer.com/2016/uuids-hibernate-mysql/
    // https://github.com/otrosien/uuid-jpa-rest-example/blob/cqrs/src/main/java/com/example/ImmutableEntity.java
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "BINARY(16) NOT NULL")
    private UUID id;

    private String address;
    private String area;
    private String url;
    private Integer price;
    private Integer fee;

    @Column(name = "broker_person_name")
    private String brokerPersonName;
    @Column(name = "broker_firm_name")
    private String brokerFirmName;
    @Column(name = "broker_firm_web_page")
    private String brokerFirmWebPage;

    private BigDecimal sqm;
    private String municipality;
    private String coordinate;
    private BigDecimal rooms;

    @Column(name = "time_removed")
    private String timeRemoved;
    @Column(name = "time_added")
    private String timeAdded;
    @Column(name = "time_updated")
    private String timeUpdated;

    @Column(name = "visit_count")
    private Integer visitCount;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "object_id",
            // no foreign key involved
            foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<ImageEntity> images = new ArrayList<>();

    @Column(name = "brf_id", columnDefinition = "BINARY(16)")
    private UUID brfId;


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

    public String getTimeRemoved() {
        return timeRemoved;
    }

    public boolean isRemoved() {
        return getTimeRemoved() != null;
    }

    public String getBrokerPersonName() {
        return brokerPersonName;
    }

    public BigDecimal getSqm() {
        return sqm;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public BigDecimal getRooms() {
        return rooms;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public Integer getFee() {
        return fee;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public String getBrokerFirmName() {
        return brokerFirmName;
    }

    public String getBrokerFirmWebPage() {
        return brokerFirmWebPage;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public UUID getBrfId() {
        return brfId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTimeRemoved(String timeRemoved) {
        this.timeRemoved = timeRemoved;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public void setBrokerPersonName(String brokerPersonName) {
        this.brokerPersonName = brokerPersonName;
    }

    public void setSqm(BigDecimal sqm) {
        this.sqm = sqm;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public void setRooms(BigDecimal rooms) {
        this.rooms = rooms;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public void setBrokerFirmName(String brokerFirmName) {
        this.brokerFirmName = brokerFirmName;
    }

    public void setBrokerFirmWebPage(String brokerFirmWebPage) {
        this.brokerFirmWebPage = brokerFirmWebPage;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public void setBrfId(UUID brfId) {
        this.brfId = brfId;
    }

    @Override
    public String toString() {
        return String.format(
                "BrEntity(%s, %s, %s)",
                id,
                address,
                area
        );
    }
}
