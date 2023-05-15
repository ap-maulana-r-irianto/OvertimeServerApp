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

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getAll"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/get/{id}"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Department getById(@PathVariable Integer id) {
        return departmentService.getById(id);
    }

    // @PostMapping
    // public Department create(@RequestBody Department department) {
    //     return departmentService.create(department);
    // }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "CREATE_ADMIN" yang dapat mengakses endpoint "/create"
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Department create(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.create(departmentRequest);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "UPDATE_ADMIN" yang dapat mengakses endpoint "/update/{id}"
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Department update(
            @PathVariable Integer id,
            @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "DELETE_ADMIN" yang dapat mengakses endpoint "/delete/{id}"
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Department delete(@PathVariable Integer id) {
        return departmentService.delete(id);
    }
}
