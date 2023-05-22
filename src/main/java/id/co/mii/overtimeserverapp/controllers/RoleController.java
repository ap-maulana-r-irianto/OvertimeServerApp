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
@PreAuthorize("hasRole('HR')")
public class RoleController {
    
    private RoleService roleService;

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_HR" yang dapat mengakses endpoint "/getAll"
    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_HR" yang dapat mengakses endpoint "/getById/{id}"
    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping("/name/{name}")
    public Role getByName(@PathVariable String name) {
        return roleService.getByName(name);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "CREATE_HR" yang dapat mengakses endpoint "/create"
    @PreAuthorize("hasAuthority('CREATE_HR')")
    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "UPDATE_HR" yang dapat mengakses endpoint "/update"
    @PreAuthorize("hasAuthority('UPDATE_HR')")
    @PutMapping("/{id}")
    public Role update(
            @PathVariable Integer id,
            @RequestBody Role role) {
        return roleService.update(id, role);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "DELETE_HR" yang dapat mengakses endpoint "/delete/{id}"
    @PreAuthorize("hasAuthority('DELETE_HR')")
    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }
}
