package se.difo.hemnetapi.api.dto.admin;

public class AdminImageRspDto extends AdminImageListRspDto.Image {

    public final String timeAdded;

    public AdminImageRspDto(
            long id,
            String objectId,
            String path,
            String timeAdded
    ) {
        super(id, objectId, path);
        this.timeAdded = timeAdded;
    }

}
