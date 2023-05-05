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

import id.co.mii.overtimeserverapp.models.Project;
import id.co.mii.overtimeserverapp.services.ProjectService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/project")
@PreAuthorize("hasRole('ADMIN')")
public class ProjectController {
    
    private ProjectService projectService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Project getById(@PathVariable Integer id) {
        return projectService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectService.create(project);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Project update(
            @PathVariable Integer id,
            @RequestBody Project project) {
        return projectService.update(id, project);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Project delete(@PathVariable Integer id) {
        return projectService.delete(id);
    }
}
