package id.co.mii.overtimeserverapp.models.dto.requests;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OvertimeRequest {
    private String description;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String status;
    private Integer employee_project_id;
}
