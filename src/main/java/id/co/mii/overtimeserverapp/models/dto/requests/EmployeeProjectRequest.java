package id.co.mii.overtimeserverapp.models.dto.requests;

import lombok.Data;

@Data
public class EmployeeProjectRequest {
    private Integer project_id;
    private Integer employee_id;
}
