package se.difo.hemnetapi.api.endpoint.admin;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.admin.AdminHouseGetRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminHouseMapper;
import se.difo.hemnetapi.core.service.HouseService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AdminHouseByUrlEndpoint extends BaseEndpoint {

    private final HouseService houseService;

    public AdminHouseByUrlEndpoint(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(
            path = "/api/v1/admin/housebyurl/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminHouseGetRspDto> get(@RequestParam(value = "url") String url) {
        return ok(AdminHouseMapper.toDto(houseService.getHouseByUrl(url)));
    }

}
