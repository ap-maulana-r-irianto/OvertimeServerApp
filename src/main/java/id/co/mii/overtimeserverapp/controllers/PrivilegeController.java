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

import id.co.mii.overtimeserverapp.models.Privilege;
import id.co.mii.overtimeserverapp.services.PrivilegeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/privilege")
@PreAuthorize("hasRole('ADMIN')")
public class PrivilegeController {

    private PrivilegeService privilegeService;

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getAll"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Privilege> getAll() {
        return privilegeService.getAll();
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getById/{id}"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Privilege getById(@PathVariable Integer id) {
        return privilegeService.getById(id);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "CREATE_ADMIN" yang dapat mengakses endpoint "/create"
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Privilege create(@RequestBody Privilege privilege) {
        return privilegeService.create(privilege);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "UPDATE_ADMIN" yang dapat mengakses endpoint "/update/{id}"
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Privilege update(
            @PathVariable Integer id,
            @RequestBody Privilege privilege) {
        return privilegeService.update(id, privilege);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "DELETE_ADMIN" yang dapat mengakses endpoint "/delete/{id}"
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Privilege delete(@PathVariable Integer id) {
        return privilegeService.delete(id);
    }
}
