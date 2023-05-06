package id.co.mii.overtimeserverapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Employee;
import id.co.mii.overtimeserverapp.models.HistoryReimburse;
import id.co.mii.overtimeserverapp.models.Reimburse;
import id.co.mii.overtimeserverapp.models.dto.requests.HistoryReimburseRequest;
import id.co.mii.overtimeserverapp.repositories.HistoryReimburseRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HistoryReimburseService {
    
    private HistoryReimburseRepository historyReimburseRepository;
    private ReimburseService reimburseService;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

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

    // public HistoryReimburse create(HistoryReimburse historyReimburse) {
    //     return historyReimburseRepository.save(historyReimburse);
    // }

    public HistoryReimburse create(HistoryReimburseRequest historyReimburseRequest) {
        HistoryReimburse historyReimburse = modelMapper.map(historyReimburseRequest, HistoryReimburse.class);
        historyReimburse.setDate_time(LocalDateTime.now());
        historyReimburse.setReimburse(reimburseService.getById(historyReimburseRequest.getReimburse_id()));
        Reimburse reimburse = reimburseService.getById(historyReimburseRequest.getReimburse_id());
        reimburse.setStatus(historyReimburse.getStatus());
        reimburseService.update(historyReimburseRequest.getReimburse_id(), reimburse);
        if (reimburse.getStatus().contains("hr")) {
            Employee employee = reimburse.getEmployee();
            int newPayroll = employee.getPayroll()+100000;
            employee.setPayroll(newPayroll);
            employeeService.update(employee.getId(), employee);
        }
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
