package art.kiroxi.cardealer.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "c_car_model")
@ToString(exclude = { "cars" })
public class CarModelEntity {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "make_id")
    private CarMakeEntity make;

    private String name;

}
