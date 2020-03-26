package spacedatahub.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacedatahub.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public void deleteByUsername(String username);

    public Optional<AppUser> findByUsername(String username);
}
