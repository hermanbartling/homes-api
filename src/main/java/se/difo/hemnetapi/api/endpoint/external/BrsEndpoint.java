package se.difo.hemnetapi.api.endpoint.external;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.external.ExtBrListRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.external.BrDtoDomainMapper;
import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.core.repo.specification.BrSpecification;
import se.difo.hemnetapi.core.service.BrService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/public/brs")
public class BrsEndpoint extends BaseEndpoint {

    private final BrService brService;

    public BrsEndpoint(BrService brService) {
        this.brService = brService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<ExtBrListRspDto> list(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "search", required = false) String search
    ) {
        pageNumber = pageNumber != null ? pageNumber : 0;
        pageSize = pageSize != null ? pageSize : 50;

        BrSpecification.Builder searchBuilder = BrSpecification.builder();
        getSearchCriterium(search).forEach(it -> searchBuilder.with(it.getKey(), it.getOperation(), it.getValue()));

        Page<Br> pages = brService.getBrs(pageNumber, pageSize, searchBuilder.build());
        return ok(BrDtoDomainMapper.toDto(
                pages.getContent(),
                pages.getNumber(),
                pages.getSize(),
                pages.getTotalPages(),
                pages.getTotalElements()
        ));
    }
}
