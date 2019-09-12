package se.difo.hemnetapi.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import se.difo.hemnetapi.core.repo.entity.HouseEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, UUID>, JpaSpecificationExecutor<HouseEntity> {

    Optional<HouseEntity> findOneById(UUID id);
    Optional<HouseEntity> findOneByUrl(String url);

}
