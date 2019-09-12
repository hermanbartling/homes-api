package se.difo.hemnetapi.core.mapper;

import se.difo.hemnetapi.core.domain.Image;
import se.difo.hemnetapi.core.repo.entity.ImageEntity;
import se.difo.hemnetapi.util.Temporal;

public class ImageMapper {

    public static Image toDomain(ImageEntity entity) {
        return new Image(
                entity.getId(),
                entity.getObjectId(),
                entity.getPath(),
                Temporal.fromDbString(entity.getTimeAdded())
        );
    }

    public static ImageEntity toEntity(Image domain) {
        ImageEntity entity = new ImageEntity();
        entity.setId(domain.getId());
        entity.setObjectId(domain.getObjectId());
        entity.setPath(domain.getPath());
        entity.setTimeAdded(Temporal.asDbString(domain.getTimeAdded()));
        return entity;
    }


}
