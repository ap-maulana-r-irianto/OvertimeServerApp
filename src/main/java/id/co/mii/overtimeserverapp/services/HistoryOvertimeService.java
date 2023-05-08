package id.co.mii.overtimeserverapp.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Employee;
import id.co.mii.overtimeserverapp.models.EmployeeProject;
import id.co.mii.overtimeserverapp.models.HistoryOvertime;
import id.co.mii.overtimeserverapp.models.Overtime;
import id.co.mii.overtimeserverapp.models.Project;
import id.co.mii.overtimeserverapp.models.dto.requests.HistoryOvertimeRequest;
import id.co.mii.overtimeserverapp.repositories.HistoryOvertimeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HistoryOvertimeService {

    private HistoryOvertimeRepository historyOvertimeRepository;
    private OvertimeService overtimeService;
    private ProjectService projectService;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

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

    // public HistoryOvertime create(HistoryOvertime historyOvertime) {
    //     return historyOvertimeRepository.save(historyOvertime);
    // }

    public HistoryOvertime create(HistoryOvertimeRequest historyOvertimeRequest) {
        HistoryOvertime historyOvertime = modelMapper.map(historyOvertimeRequest, HistoryOvertime.class);
        historyOvertime.setDate_time(LocalDateTime.now());
        historyOvertime.setOvertime(overtimeService.getById(historyOvertimeRequest.getOvertime_id()));
        Overtime overtime = overtimeService.getById(historyOvertimeRequest.getOvertime_id());
        overtime.setStatus(historyOvertime.getStatus());
        overtimeService.update(historyOvertimeRequest.getOvertime_id(), overtime);
        if (overtime.getStatus().contains("hr")) {
            Duration time = Duration.between(overtime.getEnd_time(), overtime.getStart_time());
            EmployeeProject employeeProject = overtime.getEmployeeProject();
            Project project = employeeProject.getProject();
            int newBudget = (int)(project.getBudget()-(time.toMinutes()*1000));
            project.setBudget(newBudget);
            projectService.update(project.getId(), project);
            Employee employee = employeeProject.getEmployee();
            int newPayroll = (int)(employee.getPayroll()+(time.toMinutes()*1000));
            employee.setPayroll(newPayroll);
            employeeService.update(employee.getId(), employee);
        }
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
