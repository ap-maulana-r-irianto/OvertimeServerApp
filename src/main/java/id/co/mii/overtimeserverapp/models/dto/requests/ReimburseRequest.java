package id.co.mii.overtimeserverapp.models.dto.requests;

import lombok.Data;

@Data
public class ReimburseRequest {
    private Integer nominal;
    private String description;
    private Integer employee_id;
    private Integer type_id;
}
