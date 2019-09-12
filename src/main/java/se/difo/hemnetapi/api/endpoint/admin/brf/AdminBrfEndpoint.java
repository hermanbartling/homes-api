package se.difo.hemnetapi.api.endpoint.admin.brf;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfGetRspDto;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfUpdateReqDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminBrfMapper;
import se.difo.hemnetapi.api.validate.admin.AdminBrfValidator;
import se.difo.hemnetapi.core.service.BrfService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/brfs/{id}")
public class AdminBrfEndpoint extends BaseEndpoint {

    private final BrfService brfService;

    public AdminBrfEndpoint(BrfService brfService) {
        this.brfService = brfService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminBrfGetRspDto> get(
            @PathVariable(value = "id") String id
    ) {
        return ok(AdminBrfMapper.toDto(brfService.getBrf(id)));
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminBrfGetRspDto> update(
            @PathVariable(value = "id") String id,
            @RequestBody AdminBrfUpdateReqDto body
    ) {
        AdminBrfValidator.validateUpdate(body);
        return ok(AdminBrfMapper.toDto(brfService.update(id, AdminBrfMapper.toDomain(body))));
    }

}
