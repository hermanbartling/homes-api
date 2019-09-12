package se.difo.hemnetapi.api.mapping.external;

import se.difo.hemnetapi.api.dto.common.HomeListRspDto;
import se.difo.hemnetapi.api.dto.common.PaginationDto;
import se.difo.hemnetapi.api.dto.external.ExtHouseRspDto;
import se.difo.hemnetapi.api.mapping.common.BrokerDtoDomainMapper;
import se.difo.hemnetapi.api.mapping.common.CoordinateDtoDomainMapper;
import se.difo.hemnetapi.core.domain.House;
import se.difo.hemnetapi.util.Temporal;

import java.util.List;
import java.util.stream.Collectors;

public class HouseDtoDomainMapper extends BaseMapper {

    public static HomeListRspDto toDto(
            List<House> houses,
            int currentPageNumber,
            int currentPageSize,
            int totalPageCount,
            long totalElementCount
    ) {
        List<HomeListRspDto.Home> homes = houses.stream()
                .map(HouseDtoDomainMapper::toListDto)
                .collect(Collectors.toList());

        return new HomeListRspDto(
                homes,
                new PaginationDto(
                        currentPageNumber,
                        currentPageSize,
                        totalPageCount,
                        totalElementCount
                ),
                getMapConfig(homes)
        );
    }

    private static HomeListRspDto.Home toListDto(House domain) {
        return new HomeListRspDto.Home(
                domain.getId().toString(),
                domain.getAddress(),
                domain.getArea(),
                domain.getMunicipality(),
                domain.getUrl(),
                domain.isRemoved(),
                domain.getFrontImagePath(),
                Temporal.asString(domain.getTimeAdded()),
                Temporal.asString(domain.getTimeRemoved()),
                domain.getPrice(),
                CoordinateDtoDomainMapper.toDto(domain.getCoordinate()),
                domain.getVisitCount()
        );
    }

    public static ExtHouseRspDto toDto(House domain) {
        return ExtHouseRspDto.builder()
                .id(domain.getId().toString())
                .address(domain.getAddress())
                .area(domain.getArea())
                .url(domain.getUrl())
                .isRemoved(domain.isRemoved())
                .firstImagePath(domain.getFrontImagePath())
                .price(domain.getPrice())
                .imageUrls(domain.getImagePaths())
                .municipality(domain.getMunicipality())
                .timeAdded(Temporal.asString(domain.getTimeAdded()))
                .timeRemoved(Temporal.asString(domain.getTimeRemoved()))
                .coordinate(CoordinateDtoDomainMapper.toDto(domain.getCoordinate()))
                .sqmLiving(domain.getSqmLiving())
                .sqmLand(domain.getSqmLand())
                .sqmLivingAdditional(domain.getSqmLivingAdditional())
                .rooms(domain.getRooms())
                .costYear(domain.getCostYear())
                .builtYear(domain.getBuiltYear())
                .type(domain.getType())
                .broker(BrokerDtoDomainMapper.toDto(domain.getBroker()))
                .visitCount(domain.getVisitCount())
                .build();
    }

}
