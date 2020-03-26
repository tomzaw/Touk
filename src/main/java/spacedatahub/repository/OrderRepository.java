package spacedatahub.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacedatahub.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    public Optional<List<Order>> findAllByAppUserId(Long id);
}
