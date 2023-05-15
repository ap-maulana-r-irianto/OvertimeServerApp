package id.co.mii.overtimeserverapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.overtimeserverapp.models.EmployeeProject;
import id.co.mii.overtimeserverapp.models.dto.requests.EmployeeProjectRequest;
import id.co.mii.overtimeserverapp.services.EmployeeProjectService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/employeeproject")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeProjectController {

    private EmployeeProjectService employeeProjectService;

    // memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang
    // dapat mengakses endpoint "/getAll"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<EmployeeProject> getAll() {
        return employeeProjectService.getAll();
    }

    // memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang
    // dapat mengakses endpoint "/getById/{id}"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public EmployeeProject getById(@PathVariable Integer id) {
        return employeeProjectService.getById(id);
    }

    // memastikan hanya pengguna yang memiliki otorisasi Sebagai "CREATE_ADMIN" yang
    // dapat mengakses endpoint "/create"
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public EmployeeProject create(@RequestBody EmployeeProjectRequest employeeProjectRequest) {
        return employeeProjectService.create(employeeProjectRequest);
    }

    // memastikan hanya pengguna yang memiliki otorisasi Sebagai "UPDATE_ADMIN" yang
    // dapat mengakses endpoint "/update/{id}"
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public EmployeeProject update(
            @PathVariable Integer id,
            @RequestBody EmployeeProject employeeProject) {
        return employeeProjectService.update(id, employeeProject);
    }

    // memastikan hanya pengguna yang memiliki otorisasi Sebagai "DELETE_ADMIN" yang
    // dapat mengakses endpoint "/delete/{id}"
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public EmployeeProject delete(@PathVariable Integer id) {
        return employeeProjectService.delete(id);
    }
}
