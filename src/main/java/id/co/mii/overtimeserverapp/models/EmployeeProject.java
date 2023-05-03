package id.co.mii.overtimeserverapp.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_project")
public class EmployeeProject {

    @EmbeddedId
    private EmployeeProjectPK id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_project_id", nullable = false)
    private Integer employee_project_id;
    
    @ManyToOne
    @MapsId("project_id")
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @MapsId("employee_id")
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
}
