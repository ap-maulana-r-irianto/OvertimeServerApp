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

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getAll"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<HistoryOvertime> getAll() {
        return historyOvertimeService.getAll();
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getById/{id}"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public HistoryOvertime getById(@PathVariable Integer id) {
        return historyOvertimeService.getById(id);
    }

    // @PostMapping
    // public HistoryOvertime create(@RequestBody HistoryOvertime historyOvertime) {
    //     return historyOvertimeService.create(historyOvertime);
    // }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "CREATE_ADMIN" yang dapat mengakses endpoint "/create"
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public HistoryOvertime create(@RequestBody HistoryOvertime historyOvertime) {
        return historyOvertimeService.create(historyOvertime);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "UPDATE_ADMIN" yang dapat mengakses endpoint "/update/{id}"
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public HistoryOvertime update(
            @PathVariable Integer id,
            @RequestBody HistoryOvertime historyOvertime) {
        return historyOvertimeService.update(id, historyOvertime);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "DELETE_ADMIN" yang dapat mengakses endpoint "/delete/{id}"
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public HistoryOvertime delete(@PathVariable Integer id) {
        return historyOvertimeService.delete(id);
    }
}
