package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Employee;
import id.co.mii.overtimeserverapp.models.Project;
import id.co.mii.overtimeserverapp.repositories.ProjectRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;
    private EmployeeService employeeService;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getById(Integer id) {
        return projectRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Project not found!!"));
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Integer id, Project project) {
        getById(id); // method getById
        project.setId(id);
        return projectRepository.save(project);
    }

    public Project delete(Integer id) {
        Project project = getById(id);
        projectRepository.delete(project);
        return project;
    }

    public Project addEmployee(Integer id, Employee employee) {
        Project projects = getById(id);
        List<Employee> employees = projects.getEmployee();
        employees.add(employeeService.getById(employee.getId()));
        projects.setEmployee(employees);
        return projectRepository.save(projects);
      }
}
