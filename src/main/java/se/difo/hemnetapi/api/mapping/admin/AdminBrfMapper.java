package se.difo.hemnetapi.api.mapping.admin;

import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfGetRspDto;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfListRspDto;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfUpdateReqDto;
import se.difo.hemnetapi.api.dto.common.BrfListDto;
import se.difo.hemnetapi.api.dto.common.PaginationDto;
import se.difo.hemnetapi.api.mapping.BaseMapper;
import se.difo.hemnetapi.core.domain.Brf;

import java.util.List;
import java.util.stream.Collectors;

public class AdminBrfMapper extends BaseMapper {

    public static Brf toDomain(AdminBrfCreateReqDto dto) {
        return new Brf(null, dto.name, dto.status, dto.yearRegistered, dto.memberCount);
    }

    public static Brf toDomain(AdminBrfUpdateReqDto dto) {
        return new Brf(null, null, dto.status, dto.yearRegistered, dto.memberCount);
    }

    public static AdminBrfGetRspDto toDto(Brf domain) {
        List<String> linkedBrs = domain.getBrs().stream()
                .map(br -> br.getId().toString())
                .collect(Collectors.toList());

        return new AdminBrfGetRspDto(
                domain.getId().toString(),
                domain.getName(),
                domain.getStatus(),
                domain.getRegisteredYear(),
                domain.getMemberCount(),
                linkedBrs
        );
    }

    public static AdminBrfListRspDto toListDto(
            List<Brf> domains,
            int currentPageNumber,
            int currentPageSize,
            int totalPageCount,
            long totalElementCount
    ) {
        List<BrfListDto> brfs = domains.stream()
                .map(brf -> new BrfListDto(
                        brf.getId().toString(),
                        brf.getName(),
                        brf.getStatus(),
                        brf.getRegisteredYear(),
                        brf.getMemberCount()
                ))
                .collect(Collectors.toList());

        return new AdminBrfListRspDto(
                brfs,
                new PaginationDto(currentPageNumber, currentPageSize, totalPageCount, totalElementCount)
        );
    }


}
