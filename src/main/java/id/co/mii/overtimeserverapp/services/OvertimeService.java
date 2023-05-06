package id.co.mii.overtimeserverapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Overtime;
import id.co.mii.overtimeserverapp.models.dto.requests.OvertimeRequest;
import id.co.mii.overtimeserverapp.repositories.OvertimeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OvertimeService {

    private OvertimeRepository overtimeRepository;
    private EmployeeProjectService employeeProjectService;
    private ModelMapper modelMapper;

    public List<Overtime> getAll() {
        return overtimeRepository.findAll();
    }

    public Overtime getById(Integer id) {
        return overtimeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Overtime not found!!"));
    }

    // public Overtime create(Overtime overtime) {
    //     return overtimeRepository.save(overtime);
    // }

    public Overtime create(OvertimeRequest overtimeRequest) {
        Overtime overtime = modelMapper.map(overtimeRequest, Overtime.class);
        overtime.setStart_time(LocalDateTime.now());
        overtime.setEnd_time(LocalDateTime.now());
        overtime.setStatus("pending");
        overtime.setEmployeeProject(employeeProjectService.getById(overtimeRequest.getEmployee_project_id()));
        return overtimeRepository.save(overtime);
    }

    public Overtime update(Integer id, Overtime overtime) {
        getById(id); // method getById
        overtime.setId(id);
        return overtimeRepository.save(overtime);
    }

    public Overtime delete(Integer id) {
        Overtime overtime = getById(id);
        overtimeRepository.delete(overtime);
        return overtime;
    }
}
