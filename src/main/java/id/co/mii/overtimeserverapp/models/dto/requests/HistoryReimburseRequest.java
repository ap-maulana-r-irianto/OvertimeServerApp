package id.co.mii.overtimeserverapp.models.dto.requests;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HistoryReimburseRequest {
    private LocalDateTime date_time;
    private String status;
    private String description;
    private Integer reimburse_id;
}
