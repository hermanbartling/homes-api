package se.difo.hemnetapi.api.dto.common;

import java.math.BigDecimal;

public class CoordinateDto {
    public final BigDecimal lat;
    public final BigDecimal lng;

    public CoordinateDto(BigDecimal lat, BigDecimal lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public static CoordinateDto fromString(String coordString) {
        if (coordString == null) {
            return null;
        }

        String[] coordArr = coordString
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .trim()
                .split(",");

        return new CoordinateDto(
                new BigDecimal(coordArr[0].trim()),
                new BigDecimal(coordArr[1].trim())
        );
    }
}
