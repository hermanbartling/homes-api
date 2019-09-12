package se.difo.hemnetapi.api.endpoint.admin;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.admin.AdminHouseGetRspDto;
import se.difo.hemnetapi.api.dto.admin.AdminHouseUpdateReqDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminHouseMapper;
import se.difo.hemnetapi.api.validate.admin.AdminHouseValidator;
import se.difo.hemnetapi.core.service.HouseService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/houses/{id}")
public class AdminHouseEndpoint extends BaseEndpoint {

    private final HouseService houseService;

    public AdminHouseEndpoint(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminHouseGetRspDto> get(
            @PathVariable(value = "id") String id
    ) {
        return ok(AdminHouseMapper.toDto(houseService.getHouse(id)));
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminHouseGetRspDto> update(
            @PathVariable(value = "id") String id,
            @RequestBody AdminHouseUpdateReqDto body
    ) {
        AdminHouseValidator.validateUpdate(body);
        return ok(AdminHouseMapper.toDto(houseService.update(id, AdminHouseMapper.toDomain(body))));
    }


    @RequestMapping(
            path = "/remove",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminHouseGetRspDto> markAsRemoved(
            @PathVariable(value = "id") String id
    ) {
        return ok(AdminHouseMapper.toDto(houseService.markAsRemoved(id)));
    }

}
