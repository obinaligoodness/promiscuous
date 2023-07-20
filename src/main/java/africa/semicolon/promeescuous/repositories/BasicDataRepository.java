package africa.semicolon.promeescuous.repositories;

import africa.semicolon.promeescuous.models.BasicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicDataRepository extends JpaRepository<BasicData, Long> {
}
