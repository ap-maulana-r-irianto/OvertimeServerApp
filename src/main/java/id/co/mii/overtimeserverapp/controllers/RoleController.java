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

import id.co.mii.overtimeserverapp.models.Role;
import id.co.mii.overtimeserverapp.services.RoleService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
    
    private RoleService roleService;

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getAll"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getById/{id}"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "CREATE_ADMIN" yang dapat mengakses endpoint "/create"
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "UPDATE_ADMIN" yang dapat mengakses endpoint "/update"
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Role update(
            @PathVariable Integer id,
            @RequestBody Role role) {
        return roleService.update(id, role);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "DELETE_ADMIN" yang dapat mengakses endpoint "/delete/{id}"
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }
}
