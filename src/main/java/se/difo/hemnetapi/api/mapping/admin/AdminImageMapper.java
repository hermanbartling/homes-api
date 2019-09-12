package se.difo.hemnetapi.api.mapping.admin;

import se.difo.hemnetapi.api.dto.admin.AdminImageCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.AdminImageListRspDto;
import se.difo.hemnetapi.api.dto.admin.AdminImageRspDto;
import se.difo.hemnetapi.api.dto.common.PaginationDto;
import se.difo.hemnetapi.api.mapping.BaseMapper;
import se.difo.hemnetapi.core.domain.Image;
import se.difo.hemnetapi.core.repo.entity.ImageEntity;
import se.difo.hemnetapi.util.Temporal;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AdminImageMapper extends BaseMapper {

    public static AdminImageListRspDto toDto(
            List<Image> domains,
            int currentPageNumber,
            int currentPageSize,
            int totalPageCount,
            long totalElementCount
    ) {
        List<AdminImageListRspDto.Image> images = domains.stream()
                .map(AdminImageMapper::toListDto)
                .collect(Collectors.toList());

        return new AdminImageListRspDto(
                images,
                new PaginationDto(
                        currentPageNumber,
                        currentPageSize,
                        totalPageCount,
                        totalElementCount
                )
        );
    }

    private static AdminImageListRspDto.Image toListDto(Image domain) {
        return new AdminImageListRspDto.Image(
                domain.getId(),
                domain.getObjectId().toString(),
                domain.getPath()
        );
    }

    public static AdminImageRspDto toDto(Image domain) {
        return new AdminImageRspDto(
                domain.getId(),
                domain.getObjectId().toString(),
                domain.getPath(),
                Temporal.asString(domain.getTimeAdded())
        );
    }


    public static Image toDomain(AdminImageCreateReqDto dto) {
        return new Image(null, UUID.fromString(dto.getObjectId()), dto.getPath(), Instant.now());
    }


}
