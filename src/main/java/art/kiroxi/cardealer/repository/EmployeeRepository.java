package art.kiroxi.cardealer.repository;

import art.kiroxi.cardealer.domain.EmployeeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findEmployeeEntitiesByRoleIdOrderByFullNameAsc(Long roleId);
    List<EmployeeEntity> findByRoleId(Long roleId, Sort sort);

}
