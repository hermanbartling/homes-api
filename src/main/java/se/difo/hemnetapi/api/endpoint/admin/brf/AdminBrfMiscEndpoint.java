package se.difo.hemnetapi.api.endpoint.admin.brf;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.admin.AdminBrGetRspDto;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfGetRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminBrMapper;
import se.difo.hemnetapi.api.mapping.admin.AdminBrfMapper;
import se.difo.hemnetapi.core.service.BrfService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminBrfMiscEndpoint extends BaseEndpoint {

    private final BrfService brfService;

    public AdminBrfMiscEndpoint(BrfService brfService) {
        this.brfService = brfService;
    }

    @RequestMapping(
            path = "/brfnames",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<String>> getBrfNames() {
        return ok(brfService.getBrfNames());
    }

    @RequestMapping(
            path = "/brfbyname",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminBrfGetRspDto> getByUrl(@RequestParam(value = "name") String name) {
        return ok(AdminBrfMapper.toDto(brfService.getBrfByName(name)));
    }


}
