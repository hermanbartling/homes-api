package se.difo.hemnetapi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import se.difo.hemnetapi.core.domain.Image;
import se.difo.hemnetapi.core.exception.ApiNotFoundException;
import se.difo.hemnetapi.core.exception.ApiValidationException;
import se.difo.hemnetapi.core.mapper.ImageMapper;
import se.difo.hemnetapi.core.repo.ImageRepository;
import se.difo.hemnetapi.core.repo.entity.ImageEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    private final ImageRepository imageRepository;
    private final BrService brService;
    private final HouseService houseService;

    public ImageService(
            ImageRepository imageRepository,
            BrService brService,
            HouseService houseService
    ) {
        this.imageRepository = imageRepository;
        this.brService = brService;
        this.houseService = houseService;
    }

    public Page<Image> getImages(int pageNumber, int pageSize) {
        Page<ImageEntity> page = imageRepository.findAll(
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "timeAdded"))
        );

        List<Image> images = page.getContent().stream()
                .map(ImageMapper::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(images, Pageable.unpaged(), page.getSize());
    }

    public List<Image> getImagesByObjectId(UUID objectId) {
        return imageRepository.findAllByObjectId(objectId).stream()
                .map(ImageMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id)
                .map(ImageMapper::toDomain)
                .orElseThrow(() -> new ApiNotFoundException("Image ID " + id + " not found"));

    }

    public Image add(Image toAdd) {
        Optional<ImageEntity> preExisting = imageRepository.findOneByPath(toAdd.getPath());
        if (preExisting.isPresent()) {
            ImageEntity oneAndOnly = preExisting.get();
            LOGGER.warn(
                    "Attempt to add {} when already existing {} detected, idempotent behavior kicking in",
                    toAdd,
                    oneAndOnly
            );
            return ImageMapper.toDomain(oneAndOnly);
        }

        if (!brService.brExists(toAdd.getObjectId()) && !houseService.houseExists(toAdd.getObjectId())) {
            throw new ApiValidationException("Associated image object ID " + toAdd.getObjectId() + " not found");
        }

        LOGGER.info("Adding image: " + toAdd.toString());
        Image added = ImageMapper.toDomain(imageRepository.save(ImageMapper.toEntity(toAdd)));
        LOGGER.info("Added new image {}", added);
        return added;
    }


}
