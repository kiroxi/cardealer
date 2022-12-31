package art.kiroxi.cardealer.repository;

import art.kiroxi.cardealer.domain.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // указываем фреймворку, что это репозиторий для работы с БД
// SaleEntity - сущность из БД(sale), Long - тип данных уникального ключа сущности
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    // много методов по-умолчанию уже реализованы в фреймворке findById, findBy(FIELD_NAME) и т.п.

    // найти все продажи по id клиента
    List<SaleEntity> findSaleEntitiesByClientId(Long clientId);

}
