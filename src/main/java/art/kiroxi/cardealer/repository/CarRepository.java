package art.kiroxi.cardealer.repository;

import art.kiroxi.cardealer.domain.CarEntity;
import art.kiroxi.cardealer.domain.CarMakeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findCarEntitiesByMakeOrderByStatusIdAsc(CarMakeEntity make);
    List<CarEntity> findByMake(CarMakeEntity make, Sort sort);

}
