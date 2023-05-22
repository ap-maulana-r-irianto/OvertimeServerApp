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

import id.co.mii.overtimeserverapp.models.Type;
import id.co.mii.overtimeserverapp.services.TypeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/type")
@PreAuthorize("hasRole('HR')")
public class TypeController {

    private TypeService typeService;

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_HR" yang dapat mengakses endpoint "/getAll"
    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping
    public List<Type> getAll() {
        return typeService.getAll();
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_HR" yang dapat mengakses endpoint "/getById"
    @PreAuthorize("hasAuthority('READ_HR')")
    @GetMapping("/{id}")
    public Type getById(@PathVariable Integer id) {
        return typeService.getById(id);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "CREATE_HR" yang dapat mengakses endpoint "/create"
    @PreAuthorize("hasAuthority('CREATE_HR')")
    @PostMapping
    public Type create(@RequestBody Type type) {
        return typeService.create(type);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "UPDATE_HR" yang dapat mengakses endpoint "/update"
    @PreAuthorize("hasAuthority('UPDATE_HR')")
    @PutMapping("/{id}")
    public Type update(
            @PathVariable Integer id,
            @RequestBody Type type) {
        return typeService.update(id, type);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "DELETE_HR" yang dapat mengakses endpoint "/delete"
    @PreAuthorize("hasAuthority('DELETE_HR')")
    @DeleteMapping("/{id}")
    public Type delete(@PathVariable Integer id) {
        return typeService.delete(id);
    }
}
