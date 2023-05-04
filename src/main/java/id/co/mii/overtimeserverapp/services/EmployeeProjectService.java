package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.EmployeeProject;
import id.co.mii.overtimeserverapp.repositories.EmployeeProjectRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeProjectService {
    
    private EmployeeProjectRepository employeeProjectRepository;

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
        return employeeProjectRepository.save(employeeProject);
    }

    public EmployeeProject delete(Integer id) {
        EmployeeProject employeeProject = getById(id);
        employeeProjectRepository.delete(employeeProject);
        return employeeProject;
    }
}
