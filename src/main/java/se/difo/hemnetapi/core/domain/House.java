package se.difo.hemnetapi.core.domain;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class House extends Home {

    private String type;
    private BigDecimal sqmLivingAdditional;
    private BigDecimal sqmLand;
    private Integer costYear;
    private Integer builtYear;

    public House(
            UUID id,
            String address,
            String area,
            String url,
            Integer price,
            String type,
            BigDecimal sqmLiving,
            BigDecimal sqmLivingAdditional,
            BigDecimal sqmLand,
            String municipality,
            Coordinate coordinate,
            BigDecimal rooms,
            Integer costYear,
            Integer builtYear,
            Broker broker,
            Instant timeAdded,
            Instant timeUpdated,
            Instant timeRemoved,
            List<Image> images,
            Integer visitCount
    ) {
        super(
                id,
                address,
                area,
                url,
                price,
                sqmLiving,
                municipality,
                coordinate,
                rooms,
                broker,
                timeAdded,
                timeUpdated,
                timeRemoved,
                images,
                visitCount
        );

        this.type = type;
        this.sqmLivingAdditional = sqmLivingAdditional;
        this.sqmLand = sqmLand;
        this.costYear = costYear;
        this.builtYear = builtYear;
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getSqmLivingAdditional() {
        return sqmLivingAdditional;
    }

    public BigDecimal getSqmLand() {
        return sqmLand;
    }

    public Integer getCostYear() {
        return costYear;
    }

    public Integer getBuiltYear() {
        return builtYear;
    }

    public void setTypeIfNotNull(String type) {
        if(type == null) {
            return;
        }
        this.type = type;
    }

    public void setSqmLivingAdditionalIfNotNull(BigDecimal sqmLivingAdditional) {
        if(sqmLivingAdditional == null) {
            return;
        }
        this.sqmLivingAdditional = sqmLivingAdditional;
    }

    public void setSqmLandIfNotNull(BigDecimal sqmLand) {
        if(sqmLand == null) {
            return;
        }
        this.sqmLand = sqmLand;
    }

    public void setCostYearIfNotNull(Integer costYear) {
        if(costYear == null) {
            return;
        }
        this.costYear = costYear;
    }

    public void setBuiltYearIfNotNull(Integer builtYear) {
        if(builtYear == null) {
            return;
        }
        this.builtYear = builtYear;
    }

    @Override
    public String toString() {
        return String.format(
                "House(%s, %s, %s)",
                id,
                address,
                area
        );
    }
}
