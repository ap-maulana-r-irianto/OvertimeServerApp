package id.co.mii.overtimeserverapp.models.dto.requests;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HistoryOvertimeRequest {
    private String status;
    private String description;
    private Integer overtime_id;
}
