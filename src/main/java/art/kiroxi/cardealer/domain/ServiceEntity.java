package art.kiroxi.cardealer.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "c_service")
public class ServiceEntity {

    @Id
    @SequenceGenerator(
            name = "service_seq",
            sequenceName = "c_service_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq")
    private Long id;

    private String name;

    private BigDecimal price;

}
