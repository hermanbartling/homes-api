package se.difo.hemnetapi.api.endpoint.admin;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.admin.AdminBrGetRspDto;
import se.difo.hemnetapi.api.dto.admin.AdminMinimalListRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminBrMapper;
import se.difo.hemnetapi.core.service.BrService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminBrMiscEndpoint extends BaseEndpoint {

    private final BrService brService;

    public AdminBrMiscEndpoint(BrService brService) {
        this.brService = brService;
    }

    @RequestMapping(
            path = "/brbyurl",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminBrGetRspDto> getByUrl(@RequestParam(value = "url") String url) {
        return ok(AdminBrMapper.toDto(brService.getBrByUrl(url)));
    }

    @RequestMapping(
            path = "/brsactive",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<AdminMinimalListRspDto>> getAllActive() {
        return ok(brService.getAllActive().stream()
                .map(AdminBrMapper::toMinimalListDto)
                .collect(Collectors.toList())
        );
    }

}
