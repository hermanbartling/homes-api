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
import se.difo.hemnetapi.api.dto.admin.AdminBrCreateReqDto;
import se.difo.hemnetapi.api.dto.common.HomeListRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminBrMapper;
import se.difo.hemnetapi.api.mapping.external.BrDtoDomainMapper;
import se.difo.hemnetapi.api.validate.admin.AdminBrValidator;
import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.core.repo.specification.BrSpecification;
import se.difo.hemnetapi.core.service.BrService;
import se.difo.hemnetapi.core.service.BrfService;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/brs")
public class AdminBrsEndpoint extends BaseEndpoint {

    private final BrService brService;
    private final BrfService brfService;

    public AdminBrsEndpoint(
            BrService brService,
            BrfService brfService
    ) {
        this.brService = brService;
        this.brfService = brfService;
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

        Page<Br> pages = brService.getBrs(pageNumber, pageSize, BrSpecification.builder().build());
        return ok(BrDtoDomainMapper.toDto(
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
    public ResponseEntity<Void> add(@RequestBody AdminBrCreateReqDto brCreateReqDto) {

        AdminBrValidator.validateCreate(brCreateReqDto);

        Br addedBr = brService.add(AdminBrMapper.toDomain(brCreateReqDto));

        URI locationHeader = getLocationHeader(addedBr.getId());
        return created(locationHeader).build();
    }

    private URI getLocationHeader(UUID id) {
        return UriComponentsBuilder
                .fromPath("/api/v1/admin/brs/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
