package se.difo.hemnetapi.api.endpoint.admin;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import se.difo.hemnetapi.core.domain.House;
import se.difo.hemnetapi.core.repo.specification.HouseSpecification;
import se.difo.hemnetapi.core.service.HouseService;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.dto.admin.AdminHouseCreateReqDto;
import se.difo.hemnetapi.api.dto.common.HomeListRspDto;
import se.difo.hemnetapi.api.mapping.admin.AdminHouseMapper;
import se.difo.hemnetapi.api.validate.admin.AdminHouseValidator;
import se.difo.hemnetapi.api.mapping.external.HouseDtoDomainMapper;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/houses")
public class AdminHousesEndpoint extends BaseEndpoint {

    private final HouseService houseService;

    public AdminHousesEndpoint(HouseService houseService) {
        this.houseService = houseService;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<HomeListRspDto> list(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        pageNumber = pageNumber != null ? pageNumber : 0;
        pageSize = pageSize != null ? pageSize : 50;

        Page<House> pages = houseService.getHouses(pageNumber, pageSize, HouseSpecification.builder().build());
        return ok(HouseDtoDomainMapper.toDto(
                pages.getContent(),
                pages.getNumber(),
                pages.getSize(),
                pages.getTotalPages(),
                pages.getTotalElements()
        ));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Void> add(@RequestBody AdminHouseCreateReqDto dto) {

        AdminHouseValidator.validateCreate(dto);
        House addedHouse = houseService.add(AdminHouseMapper.toDomain(dto));

        URI locationHeader = getLocationHeader(addedHouse.getId());
        return created(locationHeader).build();
    }

    private URI getLocationHeader(UUID id) {
        return UriComponentsBuilder
                .fromPath("/api/v1/admin/houses/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
