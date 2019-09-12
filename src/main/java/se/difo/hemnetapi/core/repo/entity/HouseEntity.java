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
import java.util.stream.Collectors;

@Entity(name = "house")
@Table(name = "house")
@EntityListeners(AuditingEntityListener.class)
public class HouseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "BINARY(16) NOT NULL")
    private UUID id;

    private String address;
    private String area;
    private String url;
    private Integer price;
    private String municipality;
    private String coordinate;
    @Column(name = "sqm_living")
    private BigDecimal sqmLiving;
    @Column(name = "sqm_land")
    private BigDecimal sqmLand;
    @Column(name = "sqm_living_additional")
    private BigDecimal sqmLivingAdditional;
    private BigDecimal rooms;
    @Column(name = "cost_year")
    private Integer costYear;
    @Column(name = "built_year")
    private Integer builtYear;

    @Column(name = "time_added")
    private String timeAdded;

    @Column(name = "time_removed")
    private String timeRemoved;

    @Column(name = "time_updated")
    private String timeUpdated;


    @Column(name = "broker_person_name")
    private String brokerPersonName;
    @Column(name = "broker_firm_name")
    private String brokerFirmName;
    @Column(name = "broker_firm_web_page")
    private String brokerFirmWebPage;

    private String type;

    @Column(name = "visit_count")
    private Integer visitCount;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "object_id",
            // no foreign key involved
            foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<ImageEntity> images = new ArrayList<>();

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

    public String getMunicipality() {
        return municipality;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public BigDecimal getSqmLiving() {
        return sqmLiving;
    }

    public BigDecimal getSqmLand() {
        return sqmLand;
    }

    public BigDecimal getRooms() {
        return rooms;
    }

    public Integer getCostYear() {
        return costYear;
    }

    public Integer getBuiltYear() {
        return builtYear;
    }

    public BigDecimal getSqmLivingAdditional() {
        return sqmLivingAdditional;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public String getBrokerPersonName() {
        return brokerPersonName;
    }

    public String getBrokerFirmName() {
        return brokerFirmName;
    }

    public String getBrokerFirmWebPage() {
        return brokerFirmWebPage;
    }

    public String getType() {
        return type;
    }

    public Integer getVisitCount() {
        return visitCount;
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

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public void setSqmLiving(BigDecimal sqmLiving) {
        this.sqmLiving = sqmLiving;
    }

    public void setSqmLand(BigDecimal sqmLand) {
        this.sqmLand = sqmLand;
    }

    public void setRooms(BigDecimal rooms) {
        this.rooms = rooms;
    }

    public void setCostYear(Integer costYear) {
        this.costYear = costYear;
    }

    public void setBuiltYear(Integer builtYear) {
        this.builtYear = builtYear;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }

    public void setTimeRemoved(String timeRemoved) {
        this.timeRemoved = timeRemoved;
    }

    public void setSqmLivingAdditional(BigDecimal sqmLivingAdditional) {
        this.sqmLivingAdditional = sqmLivingAdditional;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public void setBrokerPersonName(String brokerPersonName) {
        this.brokerPersonName = brokerPersonName;
    }

    public void setBrokerFirmName(String brokerFirmName) {
        this.brokerFirmName = brokerFirmName;
    }

    public void setBrokerFirmWebPage(String brokerFirmWebPage) {
        this.brokerFirmWebPage = brokerFirmWebPage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    @Override
    public String toString() {
        return String.format(
                "HouseEntity(%s, %s, %s)",
                id,
                address,
                area
        );
    }


}
