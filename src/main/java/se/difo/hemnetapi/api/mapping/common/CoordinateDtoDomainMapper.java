package se.difo.hemnetapi.api.mapping.common;

import se.difo.hemnetapi.api.dto.common.CoordinateDto;
import se.difo.hemnetapi.core.domain.Coordinate;

public class CoordinateDtoDomainMapper {

    public static CoordinateDto toDto(Coordinate domain) {
        return domain != null ? new CoordinateDto(domain.getLat(), domain.getLng()) : null;
    }

    public static Coordinate toDomain(CoordinateDto dto) {
        return dto != null ? new Coordinate(dto.lat, dto.lng) : null;
    }

}
