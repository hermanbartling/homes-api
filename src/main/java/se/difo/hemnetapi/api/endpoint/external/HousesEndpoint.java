package se.difo.hemnetapi.api.endpoint.external;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.core.domain.House;
import se.difo.hemnetapi.core.repo.specification.HouseSpecification;
import se.difo.hemnetapi.core.service.HouseService;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.dto.common.HomeListRspDto;
import se.difo.hemnetapi.api.mapping.external.HouseDtoDomainMapper;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/public/houses")
public class HousesEndpoint extends BaseEndpoint {

    private final HouseService houseService;

    public HousesEndpoint(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<HomeListRspDto> list(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "search", required = false) String search
    ) {
        pageNumber = pageNumber != null ? pageNumber : 0;
        pageSize = pageSize != null ? pageSize : 50;

        HouseSpecification.Builder searchBuilder = HouseSpecification.builder();
        getSearchCriterium(search).forEach(it -> searchBuilder.with(it.getKey(), it.getOperation(), it.getValue()));

        Page<House> pages = houseService.getHouses(pageNumber, pageSize, searchBuilder.build());
        return ok(HouseDtoDomainMapper.toDto(
                pages.getContent(),
                pages.getNumber(),
                pages.getSize(),
                pages.getTotalPages(),
                pages.getTotalElements()
        ));
    }
}
