package se.difo.hemnetapi.api.endpoint.admin;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.difo.hemnetapi.api.dto.admin.AdminImageRspDto;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.mapping.admin.AdminImageMapper;
import se.difo.hemnetapi.core.domain.Image;
import se.difo.hemnetapi.core.service.ImageService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/brs/{id}/images")
public class AdminBrImagesEndpoint extends BaseEndpoint {

    private final ImageService imageService;

    public AdminBrImagesEndpoint(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<AdminImageRspDto>> get(
            @PathVariable(value = "id") String objectId
    ) {
        List<Image> images = imageService.getImagesByObjectId(UUID.fromString(objectId));
        List<AdminImageRspDto> imageDtos = images.stream()
                .map(AdminImageMapper::toDto)
                .collect(Collectors.toList());

        return ok(imageDtos);
    }

}
