package se.difo.hemnetapi.api.dto.external;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.api.dto.common.CoordinateDto;
import se.difo.hemnetapi.api.dto.common.HomeRspDto;

import java.math.BigDecimal;
import java.util.List;

public class ExtBrRspDto extends HomeRspDto {

    public final Integer fee;
    public final String brfId;

    private ExtBrRspDto(
            String id,
            String address,
            String area,
            String url,
            boolean removed,
            String firstImageUrl,
            int price,
            List<String> imageUrls,
            String municipality,
            CoordinateDto coordinate,
            BigDecimal sqmLiving,
            BigDecimal rooms,
            String timeAdded,
            String timeRemoved,
            BrokerDto broker,
            Integer fee,
            Integer visitCount,
            String brfId
    ) {
        super(
                id,
                address,
                area,
                url,
                removed,
                firstImageUrl,
                price,
                imageUrls,
                municipality,
                coordinate,
                sqmLiving,
                rooms,
                timeAdded,
                timeRemoved,
                broker,
                visitCount
        );

        this.fee = fee;
        this.brfId = brfId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends HomeRspDto.Builder<Builder> {

        private Integer fee;
        private String brfId;

        public Builder getInstance() {
            return this;
        }

        public Builder fee(Integer fee) {
            this.fee = fee;
            return this;
        }

        public Builder brfId(String brfId) {
            this.brfId = brfId;
            return this;
        }

        public ExtBrRspDto build() {
            return new ExtBrRspDto(
                    id,
                    address,
                    area,
                    url,
                    isRemoved,
                    firstImagePath,
                    price,
                    imageUrls,
                    municipality,
                    coordinate,
                    sqmLiving,
                    rooms,
                    timeAdded,
                    timeRemoved,
                    broker,
                    fee,
                    visitCount,
                    brfId
            );
        }
    }
}
