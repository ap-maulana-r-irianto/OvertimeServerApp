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

import id.co.mii.overtimeserverapp.models.Reimburse;
import id.co.mii.overtimeserverapp.models.dto.requests.ReimburseRequest;
import id.co.mii.overtimeserverapp.services.ReimburseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/reimburse")
@PreAuthorize("hasRole('ADMIN')")
public class ReimburseController {
    
    private ReimburseService reimburseService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Reimburse> getAll() {
        return reimburseService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Reimburse getById(@PathVariable Integer id) {
        return reimburseService.getById(id);
    }

    // @PostMapping
    // public Reimburse create(@RequestBody Reimburse reimburse) {
    //     return reimburseService.create(reimburse);
    // }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Reimburse create(@RequestBody ReimburseRequest reimburseRequest, @RequestParam("file") MultipartFile file) {
        return reimburseService.create(reimburseRequest, file);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Reimburse update(
            @PathVariable Integer id,
            @RequestBody Reimburse reimburse) {
        return reimburseService.update(id, reimburse);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Reimburse delete(@PathVariable Integer id) {
        return reimburseService.delete(id);
    }
}
