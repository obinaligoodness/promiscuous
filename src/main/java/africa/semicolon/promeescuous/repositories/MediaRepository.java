package africa.semicolon.promeescuous.repositories;

import africa.semicolon.promeescuous.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
