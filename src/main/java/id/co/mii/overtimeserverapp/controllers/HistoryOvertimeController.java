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

import id.co.mii.overtimeserverapp.models.HistoryOvertime;
import id.co.mii.overtimeserverapp.services.HistoryOvertimeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/historyovertime")
@PreAuthorize("hasRole('ADMIN')")
public class HistoryOvertimeController {
    
    private HistoryOvertimeService historyOvertimeService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<HistoryOvertime> getAll() {
        return historyOvertimeService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public HistoryOvertime getById(@PathVariable Integer id) {
        return historyOvertimeService.getById(id);
    }

    @PostMapping("hasAuthority('CREATE_ADMIN')")
    public HistoryOvertime create(@RequestBody HistoryOvertime historyovertime) {
        return historyOvertimeService.create(historyovertime);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public HistoryOvertime update(
            @PathVariable Integer id,
            @RequestBody HistoryOvertime historyOvertime) {
        return historyOvertimeService.update(id, historyOvertime);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public HistoryOvertime delete(@PathVariable Integer id) {
        return historyOvertimeService.delete(id);
    }
}
