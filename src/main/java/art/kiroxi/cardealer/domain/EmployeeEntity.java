package art.kiroxi.cardealer.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
@ToString(exclude = { "sales" })
public class EmployeeEntity {

    @Id
    @SequenceGenerator(
            name = "employee_seq",
            sequenceName = "employee_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id")
    private DealerEntity dealer;

    private String phoneNumber;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manager")
    private Set<SaleEntity> sales;

}
