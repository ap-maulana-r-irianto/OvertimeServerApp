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
@PreAuthorize("hasRole('ADMIN')")
public class TypeController {

    private TypeService typeService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Type> getAll() {
        return typeService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Type getById(@PathVariable Integer id) {
        return typeService.getById(id);
    }

    @PostMapping("hasAuthority('CREATE_ADMIN')")
    public Type create(@RequestBody Type type) {
        return typeService.create(type);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Type update(
            @PathVariable Integer id,
            @RequestBody Type type) {
        return typeService.update(id, type);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Type delete(@PathVariable Integer id) {
        return typeService.delete(id);
    }
}
