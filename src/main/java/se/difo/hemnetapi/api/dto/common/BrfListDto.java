package se.difo.hemnetapi.api.dto.common;

public class BrfListDto {

    public final String id;
    public final String name;
    public final String status;
    public final Integer yearRegistered;
    public final Integer memberCount;

    public BrfListDto(String id, String name, String status, Integer yearRegistered, Integer memberCount) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.yearRegistered = yearRegistered;
        this.memberCount = memberCount;
    }
}
