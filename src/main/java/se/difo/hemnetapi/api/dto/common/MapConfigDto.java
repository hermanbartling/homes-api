package se.difo.hemnetapi.api.dto.common;

public class MapConfigDto {
    public final CoordinateDto centerCoordinate;
    public final BoundingBox boundingBox;

    public MapConfigDto(CoordinateDto centerCoordinate, BoundingBox boundingBox) {
        this.centerCoordinate = centerCoordinate;
        this.boundingBox = boundingBox;
    }

    public static class BoundingBox {
        public final CoordinateDto corner1;
        public final CoordinateDto corner2;


        public BoundingBox(CoordinateDto corner1, CoordinateDto corner2) {
            this.corner1 = corner1;
            this.corner2 = corner2;
        }

    }
}
