package id.co.mii.overtimeserverapp.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "overtime")
public class Overtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime start_time;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime end_time;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime date_time;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "employee_project_id", referencedColumnName = "id", nullable = false)
    private EmployeeProject employeeProject;
}
