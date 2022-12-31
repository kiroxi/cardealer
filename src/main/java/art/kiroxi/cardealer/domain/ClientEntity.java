package art.kiroxi.cardealer.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "client")
@ToString(exclude = { "purchases" })
public class ClientEntity {

    @Id
    @SequenceGenerator(
            name = "client_seq",
            sequenceName = "client_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String fullName;

    private String passport;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String postAddress;

    private String phoneNumber;

    private String phoneNumberAlt;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<SaleEntity> purchases;

}
