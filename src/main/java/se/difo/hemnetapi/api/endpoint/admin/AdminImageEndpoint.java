package se.difo.hemnetapi.api.endpoint.admin;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.core.service.ImageService;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.dto.admin.AdminImageRspDto;
import se.difo.hemnetapi.api.mapping.admin.AdminImageMapper;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/images/{id}")
public class AdminImageEndpoint extends BaseEndpoint {

    private final ImageService imageService;

    public AdminImageEndpoint(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminImageRspDto> get(
            @PathVariable(value = "id") Long id
    ) {
        return ok(AdminImageMapper.toDto(imageService.getImage(id)));
    }
}
