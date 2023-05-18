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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import id.co.mii.overtimeserverapp.models.Overtime;
import id.co.mii.overtimeserverapp.models.dto.requests.OvertimeRequest;
import id.co.mii.overtimeserverapp.services.OvertimeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/overtime")
@PreAuthorize("hasRole('ADMIN')")
public class OvertimeController {
    
    private OvertimeService overtimeService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Overtime> getAll() {
        return overtimeService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Overtime getById(@PathVariable Integer id) {
        return overtimeService.getById(id);
    }

    // @PostMapping
    // public Overtime create(@RequestBody Overtime overtime) {
    //     return overtimeService.create(overtime);
    // }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Overtime create(@RequestBody OvertimeRequest overtimeRequest) {
        return overtimeService.create(overtimeRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Overtime update(
            @PathVariable Integer id,
            @RequestBody Overtime overtime) {
        return overtimeService.update(id, overtime);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/approv/manager/{id}")
    public Overtime approvManager(
            @PathVariable Integer id) {
        return overtimeService.approvManager(id);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/approv/hr/{id}")
    public Overtime approvHr(
            @PathVariable Integer id) {
        return overtimeService.approvHr(id);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/reject/manager/{id}")
    public Overtime rejectManager(
            @PathVariable Integer id,
            @RequestBody String description) {
        return overtimeService.rejectManager(id, description);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/reject/hr/{id}")
    public Overtime rejectHr(
            @PathVariable Integer id,
            @RequestBody String description) {
        return overtimeService.rejectHr(id, description);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/paid/{id}")
    public Overtime paid(
            @PathVariable Integer id) {
        return overtimeService.paid(id);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Overtime delete(@PathVariable Integer id) {
        return overtimeService.delete(id);
    }
}
