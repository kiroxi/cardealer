package art.kiroxi.cardealer.domain;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "c_car_status")
@ToString(exclude = { "cars" })
public class CarStatusEntity {

    @Id
    private Long id;

    private String name;

    private String styleClass;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private Set<CarEntity> cars;

    @Transient
    public boolean isSelected(Long statusId) {
        if ( statusId != null ) {
            return statusId.equals(id);
        }
        return false;
    }

}
