package se.difo.hemnetapi.core.mapper;

import se.difo.hemnetapi.core.domain.Coordinate;

import java.math.BigDecimal;

public class CoordinateMapper {

    public static Coordinate toDomain(String entity) {
        if (entity == null) {
            return null;
        }

        String[] coordArr = entity
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .trim()
                .split(",");

        return new Coordinate(
                new BigDecimal(coordArr[0].trim()),
                new BigDecimal(coordArr[1].trim())
        );
    }

    public static String toEntity(Coordinate domain) {
        return "[" + domain.getLat() + "," + domain.getLng() + "]";
    }


}
