package se.difo.hemnetapi.api.endpoint.admin;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import se.difo.hemnetapi.core.domain.Image;
import se.difo.hemnetapi.core.repo.entity.ImageEntity;
import se.difo.hemnetapi.core.service.ImageService;
import se.difo.hemnetapi.api.endpoint.BaseEndpoint;
import se.difo.hemnetapi.api.dto.admin.AdminImageCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.AdminImageListRspDto;
import se.difo.hemnetapi.api.mapping.admin.AdminImageMapper;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin/images")
public class AdminImagesEndpoint extends BaseEndpoint {

    private final ImageService imageService;

    public AdminImagesEndpoint(ImageService imageService) {
        this.imageService = imageService;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<AdminImageListRspDto> list(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        pageNumber = pageNumber != null ? pageNumber : 0;
        pageSize = pageSize != null ? pageSize : 100;

        Page<Image> pages = imageService.getImages(pageNumber, pageSize);
        return ok(AdminImageMapper.toDto(
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
    public ResponseEntity<Void> add(@RequestBody AdminImageCreateReqDto dto) {

        Image added = imageService.add(AdminImageMapper.toDomain(dto));

        URI locationHeader = getLocationHeader(added.getId());
        return created(locationHeader).build();
    }

    private URI getLocationHeader(Long id) {
        return UriComponentsBuilder
                .fromPath("/api/v1/admin/images/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
