package se.difo.hemnetapi.api.dto.external;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.api.dto.common.CoordinateDto;
import se.difo.hemnetapi.api.dto.common.HomeRspDto;

import java.math.BigDecimal;
import java.util.List;

public class ExtHouseRspDto extends HomeRspDto {

    public final BigDecimal sqmLand;
    public final BigDecimal sqmLivingAdditional;
    public final Integer costYear;
    public final Integer builtYear;
    public final String type;

    private ExtHouseRspDto(
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
            BigDecimal sqmLand,
            BigDecimal sqmLivingAdditional,
            Integer costYear,
            Integer builtYear,
            String type,
            BrokerDto broker,
            Integer visitCount
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

        this.sqmLand = sqmLand;
        this.sqmLivingAdditional = sqmLivingAdditional;
        this.costYear = costYear;
        this.builtYear = builtYear;
        this.type = type;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends HomeRspDto.Builder<Builder> {

        private BigDecimal sqmLand;
        private BigDecimal sqmLivingAdditional;
        private Integer costYear;
        private Integer builtYear;
        private String type;

        public Builder getInstance() {
            return this;
        }

        public Builder sqmLand(BigDecimal sqmLand) {
            this.sqmLand = sqmLand;
            return this;
        }

        public Builder sqmLivingAdditional(BigDecimal sqmLivingAdditional) {
            this.sqmLivingAdditional = sqmLivingAdditional;
            return this;
        }

        public Builder costYear(Integer costYear) {
            this.costYear = costYear;
            return this;
        }

        public Builder builtYear(Integer builtYear) {
            this.builtYear = builtYear;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public ExtHouseRspDto build() {
            return new ExtHouseRspDto(
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
                    sqmLand,
                    sqmLivingAdditional,
                    costYear,
                    builtYear,
                    type,
                    broker,
                    visitCount
            );
        }
    }
}
