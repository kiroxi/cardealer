package art.kiroxi.cardealer.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "c_car_make")
public class CarMakeEntity {

    @Id
    private Long id;

    private String name;

    public String toString() {
        return "CarMakeEntity(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}
