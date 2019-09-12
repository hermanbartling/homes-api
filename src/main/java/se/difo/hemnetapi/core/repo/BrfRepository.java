package se.difo.hemnetapi.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.difo.hemnetapi.core.repo.entity.BrEntity;
import se.difo.hemnetapi.core.repo.entity.BrfEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrfRepository extends JpaRepository<BrfEntity, UUID>, JpaSpecificationExecutor<BrfEntity> {

    Optional<BrfEntity> findOneById(UUID id);

    @Query(value = "SELECT * FROM brf WHERE LOWER(name) LIKE ?1", nativeQuery = true)
    Optional<BrfEntity> findOneByName(String name);

}
