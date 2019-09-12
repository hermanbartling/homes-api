package se.difo.hemnetapi.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.difo.hemnetapi.core.repo.entity.BrEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrRepository extends JpaRepository<BrEntity, UUID>, JpaSpecificationExecutor<BrEntity> {

//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE ChallengeState SET publishedAttempts = publishedAttempts + 1 WHERE challengeId = ?1 and globalGameRoundId = ?2", nativeQuery = true)
//    void updateRetryAttempts(String challengeId, String globalGameRoundId);
//
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM ChallengeState WHERE challengeId = ?1 and globalGameRoundId = ?2", nativeQuery = true)
//    void deleteChallengeState(String challengeId, String globalGameRoundId);

    Optional<BrEntity> findOneById(UUID id);

    Optional<BrEntity> findOneByUrl(String url);

    @Query(value = "SELECT * FROM br WHERE time_removed IS NULL", nativeQuery = true)
    List<BrEntity> findAllActive();

}
