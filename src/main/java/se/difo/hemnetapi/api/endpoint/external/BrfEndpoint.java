package se.difo.hemnetapi.api.endpoint.external;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.external.ExtBrfRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.external.BrfDtoDomainMapper;
import se.difo.hemnetapi.core.service.BrfService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/public/brfs/{id}")
public class BrfEndpoint extends BaseEndpoint {

    private final BrfService brfService;

    public BrfEndpoint(BrfService brfService) {
        this.brfService = brfService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<ExtBrfRspDto> get(
            @PathVariable(value = "id") String id
    ) {
        return ok(BrfDtoDomainMapper.toDto(brfService.getBrf(id)));
    }

}
