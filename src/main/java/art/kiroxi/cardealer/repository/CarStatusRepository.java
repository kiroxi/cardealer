package art.kiroxi.cardealer.repository;

import art.kiroxi.cardealer.domain.CarStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarStatusRepository extends JpaRepository<CarStatusEntity, Long> {
}
