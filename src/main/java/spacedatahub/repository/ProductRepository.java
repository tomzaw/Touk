package spacedatahub.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spacedatahub.model.Product;
import spacedatahub.struct.ImageryType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<List<Product>> findAllByMissionName(String missionName);

    public Optional<List<Product>> findAllByMissionImageryType(ImageryType imageryType);

    public Optional<List<Product>> findAllByAcquisitionDateBefore(LocalDateTime datetime);

    public Optional<List<Product>> findAllByAcquisitionDateAfter(LocalDateTime datetime);

    public Optional<List<Product>> findAllByAcquisitionDateBetween(LocalDateTime datetime1, LocalDateTime datetime2);

    @Query(value = "SELECT product_id FROM ORDER_PRODUCT group by product_id order by count(product_id) desc limit :count", nativeQuery = true)
    public Optional<List<BigInteger>> findMostOrderedIds(@Param("count") Long count);

    @Query(value = "SELECT mission_name FROM ORDER_PRODUCT left join product on product_id=id group by mission_name order by count(mission_name) desc limit :count", nativeQuery = true)
    public Optional<List<String>> findMostOrderedMissionNames(@Param("count") Long count);
}
