package se.difo.hemnetapi.api.dto.common;

import java.math.BigDecimal;
import java.util.List;

public class HomeRspDto extends HomeListRspDto.Home {

    public final List<String> imageUrls;
    public final BigDecimal sqmLiving;
    public final BigDecimal rooms;
    public final BrokerDto broker;

    public HomeRspDto(
            String id,
            String address,
            String area,
            String url,
            boolean removed,
            String firstImageUrl,
            int price,
            List<String> imageUrls,
            String municipality,
            CoordinateDto coordinate,
            BigDecimal sqmLiving,
            BigDecimal rooms,
            String timeAdded,
            String timeRemoved,
            BrokerDto broker,
            Integer visitCount
    ) {
        super(id, address, area, municipality, url, removed, firstImageUrl, timeAdded, timeRemoved, price, coordinate, visitCount);

        this.imageUrls = imageUrls;
        this.sqmLiving = sqmLiving;
        this.rooms = rooms;
        this.broker = broker;
    }

    public abstract static class Builder<T extends Builder<T>> {

        public String id;
        public String address;
        public String area;
        public String url;
        public boolean isRemoved;
        public String firstImagePath;
        public String timeAdded;
        public String timeRemoved;

        public int price;
        public List<String> imageUrls;
        public String municipality;
        public CoordinateDto coordinate;
        public BigDecimal sqmLiving;
        public BigDecimal rooms;
        public BrokerDto broker;
        public Integer visitCount;

        public abstract T getInstance();

        public T id(String id) {
            this.id = id;
            return getInstance();
        }

        public T address(String address) {
            this.address = address;
            return getInstance();
        }

        public T area(String area) {
            this.area = area;
            return getInstance();
        }

        public T url(String url) {
            this.url = url;
            return getInstance();
        }

        public T isRemoved(boolean isRemoved) {
            this.isRemoved = isRemoved;
            return getInstance();
        }

        public T firstImagePath(String firstImagePath) {
            this.firstImagePath = firstImagePath;
            return getInstance();
        }

        public T price(int price) {
            this.price = price;
            return getInstance();
        }

        public T imageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
            return getInstance();
        }

        public T municipality(String municipality) {
            this.municipality = municipality;
            return getInstance();
        }

        public T coordinate(CoordinateDto coordinateString) {
            this.coordinate = coordinateString;
            return getInstance();
        }

        public T sqmLiving(BigDecimal sqmLiving) {
            this.sqmLiving = sqmLiving;
            return getInstance();
        }

        public T rooms(BigDecimal rooms) {
            this.rooms = rooms;
            return getInstance();
        }

        public T timeAdded(String timeAdded) {
            this.timeAdded = timeAdded;
            return getInstance();
        }

        public T timeRemoved(String timeRemoved) {
            this.timeRemoved = timeRemoved;
            return getInstance();
        }

        public T broker(BrokerDto broker) {
            this.broker = broker;
            return getInstance();
        }

        public T visitCount(Integer visitCount) {
            this.visitCount = visitCount;
            return getInstance();
        }

    }

}
