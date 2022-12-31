package art.kiroxi.cardealer.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @SequenceGenerator(
            name = "car_seq",
            sequenceName = "car_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    private Long id;

    private String vin;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "make_id")
    private CarMakeEntity make;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private CarModelEntity model;

    private Integer year;

    private String color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_id")
    private DealerEntity dealer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private CarStatusEntity status;

    private BigDecimal price;

}
