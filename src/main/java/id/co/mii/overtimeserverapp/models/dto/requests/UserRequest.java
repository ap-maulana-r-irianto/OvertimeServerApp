package id.co.mii.overtimeserverapp.models.dto.requests;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
}
