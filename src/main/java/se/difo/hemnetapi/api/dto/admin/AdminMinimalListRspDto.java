package se.difo.hemnetapi.api.dto.admin;

public class AdminMinimalListRspDto {

    public final String id;
    public final String url;
    public final String timeAdded;
    public final String timeUpdated;
    public final String timeRemoved;

    public AdminMinimalListRspDto(String id, String url, String timeAdded, String timeUpdated, String timeRemoved) {
        this.id = id;
        this.url = url;
        this.timeAdded = timeAdded;
        this.timeUpdated = timeUpdated;
        this.timeRemoved = timeRemoved;
    }
}
