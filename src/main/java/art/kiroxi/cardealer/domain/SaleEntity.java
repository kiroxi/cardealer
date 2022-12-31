package art.kiroxi.cardealer.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data // автоматическая генерация шаблонного когда get/set
@Entity // сущность
@Table(name = "sale") // таблица sale
public class SaleEntity {

    @Id // уникальный ключ сущности
    @SequenceGenerator(
            name = "sale_seq", // имя генератора последовательности
            sequenceName = "sale_id_seq", // название последовательности в БД
            allocationSize = 1 // шаг значений
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq")
    // автоматическая генерация уникального id на основе последовательности в БД
    private Long id;

    private BigDecimal amount;

    // отношение 1..1 на таблицу car
    @OneToOne
    @JoinColumn(name = "car_id") // связка по полю car_id
    private CarEntity car;

    // отношение n..1 к таблице client
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id") // связка по полю client_id
    private ClientEntity client;

    // отношение n..1 к таблице employee
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id") // связка по полю manager_id
    private EmployeeEntity manager;

    // отношение n..1 к таблице employee
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_id") // связка по полю dealer_id
    private DealerEntity dealer;

    @Temporal(TemporalType.TIMESTAMP) // указани, что поле как timestamp (со временем)
    private Date saleDate;

}
