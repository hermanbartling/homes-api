package se.difo.hemnetapi.api.dto.common;

import java.util.List;
import java.util.Objects;

public class HomeListRspDto {

    public final List<? extends Home> homes;
    public final PaginationDto pagination;
    public final MapConfigDto mapConfig;

    public HomeListRspDto(
            List<? extends Home> homes,
            PaginationDto pagination,
            MapConfigDto mapConfig
    ) {
        this.homes = homes;
        this.pagination = pagination;
        this.mapConfig = mapConfig;
    }

    public static class Home {
        public final String id;
        public final String address;
        public final String area;
        public final String municipality;
        public final String url;
        public final boolean removed;
        public final String firstImageUrl;
        public final String timeAdded;
        public final String timeRemoved;
        public final Integer price;
        public final CoordinateDto coordinate;
        public final Integer visitCount;

        public Home(
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
                Integer visitCount
        ) {

            Objects.requireNonNull(address, "address can't be null");
            Objects.requireNonNull(area, "area can't be null");
            Objects.requireNonNull(url, "url can't be null");
            Objects.requireNonNull(timeAdded, "timeAdded can't be null");
            Objects.requireNonNull(price, "price can't be null");

            this.id = id;
            this.address = address;
            this.municipality = municipality;
            this.area = area;
            this.url = url;
            this.removed = removed;
            this.firstImageUrl = firstImageUrl;
            this.timeAdded = timeAdded;
            this.timeRemoved = timeRemoved;
            this.price = price;
            this.coordinate = coordinate;
            this.visitCount = visitCount;
        }

        public String getId() {
            return id;
        }

        public String getAddress() {
            return address;
        }

        public String getArea() {
            return area;
        }

        public String getMunicipality() {
            return municipality;
        }

        public String getUrl() {
            return url;
        }

        public boolean isRemoved() {
            return removed;
        }

        public String getFirstImageUrl() {
            return firstImageUrl;
        }

        public String getTimeAdded() {
            return timeAdded;
        }

        public String getTimeRemoved() {
            return timeRemoved;
        }

        public Integer getPrice() {
            return price;
        }

        public CoordinateDto getCoordinate() {
            return coordinate;
        }

    }

}
