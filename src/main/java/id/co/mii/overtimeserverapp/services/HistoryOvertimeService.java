package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.HistoryOvertime;
import id.co.mii.overtimeserverapp.repositories.HistoryOvertimeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HistoryOvertimeService {

    private HistoryOvertimeRepository historyOvertimeRepository;

    public List<HistoryOvertime> getAll() {
        return historyOvertimeRepository.findAll();
    }

    public HistoryOvertime getById(Integer id) {
        return historyOvertimeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "HistoryOvertime not found!!"));
    }

    public HistoryOvertime create(HistoryOvertime historyOvertime) {
        return historyOvertimeRepository.save(historyOvertime);
    }

    public HistoryOvertime update(Integer id, HistoryOvertime historyOvertime) {
        getById(id); // method getById
        historyOvertime.setId(id);
        return historyOvertimeRepository.save(historyOvertime);
    }

    public HistoryOvertime delete(Integer id) {
        HistoryOvertime employee = getById(id);
        historyOvertimeRepository.delete(employee);
        return employee;
    }
}