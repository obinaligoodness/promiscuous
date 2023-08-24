package africa.semicolon.promeescuous.repositories;

import africa.semicolon.promeescuous.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
    boolean existsByUser(User user);

    @Query("SELECT m FROM Media m WHERE m.user = :user AND m.isLike = true")
    Optional<Media> findMediaByUserAndIsLikeIsTrue(User user);
}
