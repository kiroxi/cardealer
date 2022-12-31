package art.kiroxi.cardealer.forms;

import art.kiroxi.cardealer.domain.CarEntity;
import art.kiroxi.cardealer.domain.DealerEntity;
import lombok.Data;

@Data // генерация шаблонного кода get/set
public class SaleForm {

    private Long id;
    private Long carId; // id машины
    private Long clientId; // id клиента
    private Long managerId; // id сотрудника
    private Long dealerId; // id автосалона

}
