package se.difo.hemnetapi.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.difo.hemnetapi.core.repo.entity.ImageEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    List<ImageEntity> findAllByObjectId(UUID objectId);

    Optional<ImageEntity> findOneByPath(String path);

}
