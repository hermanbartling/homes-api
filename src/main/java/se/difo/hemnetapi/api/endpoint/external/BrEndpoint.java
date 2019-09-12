package se.difo.hemnetapi.api.endpoint.external;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.external.ExtBrRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.external.BrDtoDomainMapper;
import se.difo.hemnetapi.core.service.BrService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/public/brs/{id}")
public class BrEndpoint extends BaseEndpoint {

    private final BrService brService;

    public BrEndpoint(BrService brService) {
        this.brService = brService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<ExtBrRspDto> get(
            @PathVariable(value = "id") String id
    ) {
        return ok(BrDtoDomainMapper.toDto(brService.getBr(id)));
    }

}
