package se.difo.hemnetapi.api.mapping.external;

import se.difo.hemnetapi.api.dto.common.PaginationDto;
import se.difo.hemnetapi.api.dto.external.ExtBrListRspDto;
import se.difo.hemnetapi.api.dto.external.ExtBrRspDto;
import se.difo.hemnetapi.api.mapping.common.BrokerDtoDomainMapper;
import se.difo.hemnetapi.api.mapping.common.CoordinateDtoDomainMapper;
import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.util.Temporal;

import java.util.List;
import java.util.stream.Collectors;

public class BrDtoDomainMapper extends BaseMapper {

    public static ExtBrListRspDto toDto(
            List<Br> brs,
            int currentPageNumber,
            int currentPageSize,
            int totalPageCount,
            long totalElementCount
    ) {
        List<ExtBrListRspDto.BrHome> homes = brs.stream()
                .map(BrDtoDomainMapper::toListDto)
                .collect(Collectors.toList());

        return new ExtBrListRspDto(
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

    private static ExtBrListRspDto.BrHome toListDto(Br domain) {
        return new ExtBrListRspDto.BrHome(
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
                domain.getSqmLiving(),
                Math.round(domain.getPrice() / domain.getSqmLiving().intValue()),
                domain.getFee(),
                domain.getRooms(),
                BrokerDtoDomainMapper.toDto(domain.getBroker()),
                domain.getVisitCount()
        );
    }

    public static ExtBrRspDto toDto(Br domain) {
        return ExtBrRspDto.builder()
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
                .rooms(domain.getRooms())
                .broker(BrokerDtoDomainMapper.toDto(domain.getBroker()))
                .fee(domain.getFee())
                .visitCount(domain.getVisitCount())
                .brfId(domain.getBrfId() != null ? domain.getBrfId().toString() : null)
                .build();
    }


}
