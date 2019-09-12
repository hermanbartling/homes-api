package se.difo.hemnetapi.api.mapping.external;

import se.difo.hemnetapi.api.dto.common.CoordinateDto;
import se.difo.hemnetapi.api.dto.common.HomeListRspDto;
import se.difo.hemnetapi.api.dto.common.MapConfigDto;
import se.difo.hemnetapi.api.dto.external.ExtBrListRspDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;

abstract class BaseMapper {


    private static CoordinateDto getCenterCoordinate(List<BigDecimal> lats, List<BigDecimal> lngs) {
        return new CoordinateDto(
                (min(lats).add(max(lats))).divide(BigDecimal.valueOf(2), BigDecimal.ROUND_DOWN),
                (min(lngs).add(max(lngs))).divide(BigDecimal.valueOf(2), BigDecimal.ROUND_DOWN)

        );
    }

    private static MapConfigDto.BoundingBox getBoundingBox(
            List<BigDecimal> lats,
            List<BigDecimal> lngs
    ) {
        return new MapConfigDto.BoundingBox(
                new CoordinateDto(min(lats), min(lngs)),
                new CoordinateDto(max(lats), max(lngs))
        );
    }

    static MapConfigDto getMapConfig(List<? extends HomeListRspDto.Home> homes) {
        if (homes.isEmpty()) {
            return null;
        }
        List<BigDecimal> lats = new ArrayList<>();
        List<BigDecimal> lngs = new ArrayList<>();
        homes.forEach(h -> {
            if (h.getCoordinate() != null) {
                lats.add(h.getCoordinate().lat);
                lngs.add(h.getCoordinate().lng);
            }
        });

        return new MapConfigDto(
                getCenterCoordinate(lats, lngs),
                getBoundingBox(lats, lngs)
        );
    }


}
