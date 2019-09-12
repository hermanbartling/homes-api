package se.difo.hemnetapi.api.endpoint.external;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.core.service.HouseService;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.dto.external.ExtHouseRspDto;
import se.difo.hemnetapi.api.mapping.external.HouseDtoDomainMapper;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/public/houses/{id}")
public class HouseEndpoint extends BaseEndpoint {

    private final HouseService houseService;

    public HouseEndpoint(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<ExtHouseRspDto> get(
            @PathVariable(value = "id") String id
    ) {
        return ok(HouseDtoDomainMapper.toDto(houseService.getHouse(id)));
    }
}
