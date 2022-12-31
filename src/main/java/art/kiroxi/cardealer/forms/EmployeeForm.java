package art.kiroxi.cardealer.forms;

import lombok.Data;

@Data
public class EmployeeForm {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long roleId;
    private Long dealerId;
    private String phoneNumber;
    private String email;

}
