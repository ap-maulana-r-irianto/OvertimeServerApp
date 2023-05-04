package id.co.mii.overtimeserverapp.models.dto.requests;

import lombok.Data;

@Data
public class DepartmentRequest {
    private String name;
    private Integer manager_id;
    private Integer hr_id;
}
