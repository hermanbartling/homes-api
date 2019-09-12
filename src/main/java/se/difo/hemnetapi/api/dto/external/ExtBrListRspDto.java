package se.difo.hemnetapi.api.dto.external;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.api.dto.common.CoordinateDto;
import se.difo.hemnetapi.api.dto.common.HomeListRspDto;
import se.difo.hemnetapi.api.dto.common.MapConfigDto;
import se.difo.hemnetapi.api.dto.common.PaginationDto;

import java.math.BigDecimal;
import java.util.List;

public class ExtBrListRspDto extends HomeListRspDto {

    public ExtBrListRspDto(
            List<BrHome> homes,
            PaginationDto pagination,
            MapConfigDto mapConfig
    ) {
        super(
                homes,
                pagination,
                mapConfig
        );

    }

    public static class BrHome extends HomeListRspDto.Home {
        public final BigDecimal sqmLiving;
        public final Integer pricePerSqm;
        public final Integer fee;
        public final BigDecimal rooms;
        public final BrokerDto broker;

        public BrHome(
                String id,
                String address,
                String area,
                String municipality,
                String url,
                boolean removed,
                String firstImageUrl,
                String timeAdded,
                String timeRemoved,
                Integer price,
                CoordinateDto coordinate,
                BigDecimal sqmLiving,
                Integer pricePerSqm,
                Integer fee,
                BigDecimal rooms,
                BrokerDto broker,
                Integer visitCount

        ) {
            super(
                    id,
                    address,
                    area,
                    municipality,
                    url,
                    removed,
                    firstImageUrl,
                    timeAdded,
                    timeRemoved,
                    price,
                    coordinate,
                    visitCount
            );
            this.sqmLiving = sqmLiving;
            this.pricePerSqm = pricePerSqm;
            this.fee = fee;
            this.rooms = rooms;
            this.broker = broker;
        }

    }

//    public static Builder builder() {
//        return new Builder();
//    }
//
//    public static class Builder extends HomeRspDto.Builder<Builder> {
//
//        private Integer fee;
//
//        public Builder getInstance() {
//            return this;
//        }
//
//        public Builder fee(Integer fee) {
//            this.fee = fee;
//            return this;
//        }
//
//        public ExtBrListRspDto build() {
//            return new ExtBrListRspDto(
//                    id,
//                    address,
//                    area,
//                    url,
//                    isRemoved,
//                    firstImagePath,
//                    price,
//                    imageUrls,
//                    municipality,
//                    coordinate,
//                    sqmLiving,
//                    rooms,
//                    timeAdded,
//                    timeRemoved,
//                    broker,
//                    fee
//            );
//        }
//    }
}
