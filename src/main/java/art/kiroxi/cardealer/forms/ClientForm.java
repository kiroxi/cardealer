package art.kiroxi.cardealer.forms;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ClientForm {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String postAddress;
    private String phoneNumber;
    private String phoneNumberAlt;
    private String email;

}
