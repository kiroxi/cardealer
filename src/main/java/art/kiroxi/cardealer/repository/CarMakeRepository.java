package art.kiroxi.cardealer.repository;

import art.kiroxi.cardealer.domain.CarMakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMakeRepository extends JpaRepository<CarMakeEntity, Long> {

    CarMakeEntity findByNameIgnoreCase(String name);

}
