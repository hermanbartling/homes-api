package se.difo.hemnetapi.api.dto.admin;

public class AdminImageCreateReqDto {

    private String objectId;
    private String path;

    public AdminImageCreateReqDto() {
    }

    public AdminImageCreateReqDto(String objectId, String path) {
        this.objectId = objectId;
        this.path = path;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
