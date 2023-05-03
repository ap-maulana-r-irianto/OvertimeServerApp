package id.co.mii.overtimeserverapp.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class EmployeeProjectPK implements Serializable{
    @Column(name = "project_id")
    private int project_id;

    @Column(name = "employee_id")
    private int employee_id;
}
