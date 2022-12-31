package art.kiroxi.cardealer.repository;

import art.kiroxi.cardealer.domain.DealerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<DealerEntity, Long> {
}
