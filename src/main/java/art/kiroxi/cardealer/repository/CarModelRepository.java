package art.kiroxi.cardealer.repository;

import art.kiroxi.cardealer.domain.CarMakeEntity;
import art.kiroxi.cardealer.domain.CarModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModelEntity, Long> {

    List<CarModelEntity> findCarModelEntitiesByMake(CarMakeEntity carMakeEntity);

}
