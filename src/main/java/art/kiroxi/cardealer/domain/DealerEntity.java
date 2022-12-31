package art.kiroxi.cardealer.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "dealer")
@ToString(exclude = { "employees", "cars", "sales" })
public class DealerEntity {

    @Id
    private Long id;

    private String name;

    private String inn;

    private String address;

    private String phoneNumber;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dealer")
    private Set<EmployeeEntity> employees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dealer")
    private Set<CarEntity> cars;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dealer")
    private Set<SaleEntity> sales;

    @Transient
    public boolean isSelected(Long dealerId) {
        if ( dealerId != null ) {
            return dealerId.equals(id);
        }
        return false;
    }

}
