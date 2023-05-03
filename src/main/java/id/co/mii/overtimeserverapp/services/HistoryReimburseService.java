package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.HistoryReimburse;
import id.co.mii.overtimeserverapp.repositories.HistoryReimburseRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HistoryReimburseService {
    
    private HistoryReimburseRepository historyReimburseRepository;

    public List<HistoryReimburse> getAll() {
        return historyReimburseRepository.findAll();
    }

    public HistoryReimburse getById(Integer id) {
        return historyReimburseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "HistoryReimburse not found!!"));
    }

    public HistoryReimburse create(HistoryReimburse historyReimburse) {
        return historyReimburseRepository.save(historyReimburse);
    }

    public HistoryReimburse update(Integer id, HistoryReimburse historyReimburse) {
        getById(id); // method getById
        historyReimburse.setId(id);
        return historyReimburseRepository.save(historyReimburse);
    }

    public HistoryReimburse delete(Integer id) {
        HistoryReimburse employee = getById(id);
        historyReimburseRepository.delete(employee);
        return employee;
    }
}
