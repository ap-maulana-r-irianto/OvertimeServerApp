package id.co.mii.overtimeserverapp.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(unique = true, name = "email", nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "payroll", nullable = false)
    private int payroll;

    @OneToMany(mappedBy="manager")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Employee> managerTop;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy="manager")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Department> managerDept;

    @OneToMany(mappedBy="hr")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Department> hr;

    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy="employee")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<EmployeeProject> employeeProject;
}
