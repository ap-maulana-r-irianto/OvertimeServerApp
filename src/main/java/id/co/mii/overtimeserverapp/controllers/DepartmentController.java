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

import id.co.mii.overtimeserverapp.models.Department;
import id.co.mii.overtimeserverapp.models.dto.requests.DepartmentRequest;
import id.co.mii.overtimeserverapp.services.DepartmentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/department")
@PreAuthorize("hasRole('ADMIN')")
public class DepartmentController {
    
    private DepartmentService departmentService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Department getById(@PathVariable Integer id) {
        return departmentService.getById(id);
    }

    // @PostMapping("hasAuthority('CREATE_ADMIN')")
    // public Department create(@RequestBody Department department) {
    //     return departmentService.create(department);
    // }

    @PostMapping("hasAuthority('CREATE_ADMIN')")
    public Department create(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.create(departmentRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Department update(
            @PathVariable Integer id,
            @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Department delete(@PathVariable Integer id) {
        return departmentService.delete(id);
    }
}
