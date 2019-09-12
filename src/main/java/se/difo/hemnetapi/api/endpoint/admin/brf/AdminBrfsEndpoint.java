package se.difo.hemnetapi.api.endpoint.admin.brf;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfListRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminBrfMapper;
import se.difo.hemnetapi.api.validate.admin.AdminBrfValidator;
import se.difo.hemnetapi.core.domain.Brf;
import se.difo.hemnetapi.core.repo.specification.BrfSpecification;
import se.difo.hemnetapi.core.service.BrfService;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/brfs")
public class AdminBrfsEndpoint extends BaseEndpoint {

    private final BrfService brfService;

    public AdminBrfsEndpoint(BrfService brfService) {
        this.brfService = brfService;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminBrfListRspDto> list(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        pageNumber = pageNumber != null ? pageNumber : 0;
        pageSize = pageSize != null ? pageSize : 100;

        Page<Brf> pages = brfService.getBrfs(pageNumber, pageSize, BrfSpecification.builder().build());
        return ok(AdminBrfMapper.toListDto(
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
    public ResponseEntity<Void> add(@RequestBody AdminBrfCreateReqDto brfCreateReqDto) {

        AdminBrfValidator.validateCreate(brfCreateReqDto);

        Brf added = brfService.add(AdminBrfMapper.toDomain(brfCreateReqDto));

        URI locationHeader = getLocationHeader(added.getId());
        return created(locationHeader).build();
    }

    private URI getLocationHeader(UUID id) {
        return UriComponentsBuilder
                .fromPath("/api/v1/admin/brfs/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
