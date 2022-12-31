package art.kiroxi.cardealer.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "c_role")
@ToString(exclude = { "employees" })
public class RoleEntity {

    @Id
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<EmployeeEntity> employees;

    @Transient
    public boolean isSelected(Long roleId) {
        if ( roleId != null ) {
            return roleId.equals(id);
        }
        return false;
    }

}
