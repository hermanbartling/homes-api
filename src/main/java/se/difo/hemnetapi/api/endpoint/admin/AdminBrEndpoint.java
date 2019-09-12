package se.difo.hemnetapi.api.endpoint.admin;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.admin.AdminBrGetRspDto;
import se.difo.hemnetapi.api.dto.admin.AdminBrUpdateReqDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminBrMapper;
import se.difo.hemnetapi.api.validate.admin.AdminBrValidator;
import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.core.service.BrService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/brs/{id}")
public class AdminBrEndpoint extends BaseEndpoint {

    private final BrService brService;

    public AdminBrEndpoint(BrService brService) {
        this.brService = brService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminBrGetRspDto> get(
            @PathVariable(value = "id") String id
    ) {
        return ok(AdminBrMapper.toDto(brService.getBr(id)));
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminBrGetRspDto> update(
            @PathVariable(value = "id") String id,
            @RequestBody AdminBrUpdateReqDto body
    ) {
        AdminBrValidator.validateUpdate(body);
        return ok(AdminBrMapper.toDto(brService.update(id, AdminBrMapper.toDomain(body))));
    }

    @RequestMapping(
            path = "/remove",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminBrGetRspDto> markAsRemoved(
            @PathVariable(value = "id") String id
    ) {
        return ok(AdminBrMapper.toDto(brService.markAsRemoved(id)));
    }

}
