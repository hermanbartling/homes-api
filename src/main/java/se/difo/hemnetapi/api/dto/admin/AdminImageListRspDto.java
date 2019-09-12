package se.difo.hemnetapi.api.dto.admin;

import se.difo.hemnetapi.api.dto.common.PaginationDto;

import java.util.List;

public class AdminImageListRspDto {

    public final List<Image> images;
    public final PaginationDto pagination;

    public AdminImageListRspDto(
            List<Image> images,
            PaginationDto pagination
    ) {
        this.images = images;
        this.pagination = pagination;
    }

    public static class Image {
        public final long id;
        public final String objectId;
        public final String path;

        public Image(long id, String objectId, String path) {
            this.id = id;
            this.objectId = objectId;
            this.path = path;
        }
    }

}
