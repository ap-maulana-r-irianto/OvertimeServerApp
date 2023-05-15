package id.co.mii.overtimeserverapp.models.dto.requests;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String phone;
    private String email;
    private String account_bank;
    private String username;
    private String password;
    private Integer role_id;
    private Integer department_id;
    private Integer manager_id;
}
