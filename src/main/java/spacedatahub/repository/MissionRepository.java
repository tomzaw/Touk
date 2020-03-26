package spacedatahub.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacedatahub.model.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    public void deleteByName(String name);

    public Optional<Mission> findByName(String name);
}
