package se.difo.hemnetapi.core.domain;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Br extends Home {

    private Integer fee;
    private UUID brfId;

    public Br(
            UUID id,
            String address,
            String area,
            String url,
            Integer price,
            Integer fee,
            BigDecimal sqmLiving,
            String municipality,
            Coordinate coordinate,
            BigDecimal rooms,
            Broker broker,
            Instant timeAdded,
            Instant timeUpdated,
            Instant timeRemoved,
            List<Image> images,
            Integer visitCount,
            UUID brfId
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

        this.fee = fee;
        this.images = images;
        this.brfId = brfId;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFeeIfNotNull(Integer fee) {
        if (fee == null) {
            return;
        }
        this.fee = fee;
    }

    public UUID getBrfId() {
        return brfId;
    }

    public void setBrfIdIfNotNull(UUID brfId) {
        if (brfId == null) {
            return;
        }
        this.brfId = brfId;
    }

    @Override
    public String toString() {
        return String.format(
                "Br(%s, %s, %s)",
                id,
                address,
                area
        );
    }
}
