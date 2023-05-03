package id.co.mii.overtimeserverapp.models.dto.requests;

import lombok.Data;

@Data
public class LoginRequest {

  private String username;
  private String password;
}