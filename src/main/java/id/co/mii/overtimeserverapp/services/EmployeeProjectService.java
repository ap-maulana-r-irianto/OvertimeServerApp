package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.EmployeeProject;
import id.co.mii.overtimeserverapp.models.dto.requests.EmployeeProjectRequest;
import id.co.mii.overtimeserverapp.repositories.EmployeeProjectRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeProjectService {
    
    private EmployeeProjectRepository employeeProjectRepository;
    private ProjectService projectService;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public List<EmployeeProject> getAll() {
        return employeeProjectRepository.findAll();
    }

    public EmployeeProject getById(Integer id) {
        return employeeProjectRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "EmployeeProject not found!!"));
    }

    public EmployeeProject create(EmployeeProject employeeProject) {
        // EmployeeProject employeeProject = modelMapper.map(employeeProjectRequest, EmployeeProject.class);
        // employeeProject.setProject(projectService.getById(employeeProjectRequest.getProject_id()));
        // employeeProject.setEmployee(employeeService.getById(employeeProjectRequest.getEmployee_id()));
        return employeeProjectRepository.save(employeeProject);
    }

    public EmployeeProject update(Integer id, EmployeeProject employeeProject) {
        getById(id); // method getById
        employeeProject.setId(id);
        return employeeProjectRepository.save(employeeProject);
    }

    public EmployeeProject delete(Integer id) {
        EmployeeProject employeeProject = getById(id);
        employeeProjectRepository.delete(employeeProject);
        return employeeProject;
    }
}
