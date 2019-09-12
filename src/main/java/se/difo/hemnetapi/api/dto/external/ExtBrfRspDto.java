package se.difo.hemnetapi.api.dto.external;

import java.util.List;

public class ExtBrfRspDto {

    public final String id;
    public final String name;
    public final String status;
    public final Integer yearRegistered;
    public final Integer memberCount;
    public final List<String> brIds;

    public ExtBrfRspDto(String id, String name, String status, Integer yearRegistered, Integer memberCount, List<String> brIds) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.yearRegistered = yearRegistered;
        this.memberCount = memberCount;
        this.brIds = brIds;
    }
}
