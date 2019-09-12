package se.difo.hemnetapi.api.dto.common;

import java.util.List;

public class BrfDto extends BrfListDto {

    public final List<String> linkedBrs;

    public BrfDto(String id, String name, String status, Integer yearRegistered, Integer memberCount, List<String> linkedBrs) {
        super(id, name, status, yearRegistered, memberCount);
        this.linkedBrs = linkedBrs;
    }
}
