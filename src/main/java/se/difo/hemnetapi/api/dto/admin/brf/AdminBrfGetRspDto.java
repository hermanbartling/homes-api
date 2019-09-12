package se.difo.hemnetapi.api.dto.admin.brf;

import se.difo.hemnetapi.api.dto.common.BrfDto;

import java.util.List;

public class AdminBrfGetRspDto extends BrfDto {

    public AdminBrfGetRspDto(String id, String name, String status, Integer yearRegistered, Integer memberCount, List<String> linkedBrs) {
        super(id, name, status, yearRegistered, memberCount, linkedBrs);
    }
}
