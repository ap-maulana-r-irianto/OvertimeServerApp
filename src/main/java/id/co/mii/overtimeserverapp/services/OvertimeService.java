package id.co.mii.overtimeserverapp.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Employee;
import id.co.mii.overtimeserverapp.models.EmployeeProject;
import id.co.mii.overtimeserverapp.models.HistoryOvertime;
import id.co.mii.overtimeserverapp.models.Overtime;
import id.co.mii.overtimeserverapp.models.Project;
import id.co.mii.overtimeserverapp.models.dto.requests.OvertimeRequest;
import id.co.mii.overtimeserverapp.repositories.OvertimeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OvertimeService {

    private OvertimeRepository overtimeRepository;
    private EmployeeProjectService employeeProjectService;
    private StatusService statusService;
    private HistoryOvertimeService historyOvertimeService;
    private ProjectService projectService;
    private EmployeeService employeeService;
    private EmailService emailService;
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
    // return overtimeRepository.save(overtime);
    // }

    public Overtime create(OvertimeRequest overtimeRequest) {
        Overtime overtime = modelMapper.map(overtimeRequest, Overtime.class);
        overtime.setStatus(statusService.getById(1));
        overtime.setEmployeeProject(employeeProjectService.getById(overtimeRequest.getEmployee_project_id()));
        Duration time = Duration.between(overtime.getEnd_time(), overtime.getStart_time());
        int nominal = (int) ((time.toMinutes() * 1000) * (-1));
        overtime.setNominal(nominal);
        overtime = overtimeRepository.save(overtime);
        HistoryOvertime historyOvertime = new HistoryOvertime();
        historyOvertime.setDate_time(LocalDateTime.now());
        historyOvertime.setStatus(overtime.getStatus().getName());
        historyOvertime.setOvertime(overtime);
        historyOvertime.setDescription("start submission");
        historyOvertimeService.create(historyOvertime);
        return overtime;
    }

    public Overtime update(Integer id, Overtime overtime) {
        getById(id); // method getById
        overtime.setId(id);
        overtime = overtimeRepository.save(overtime);
        HistoryOvertime historyOvertime = new HistoryOvertime();
        historyOvertime.setDate_time(LocalDateTime.now());
        historyOvertime.setStatus(overtime.getStatus().getName());
        historyOvertime.setOvertime(overtime);
        historyOvertime.setDescription(overtime.getDescription());
        historyOvertimeService.create(historyOvertime);
        return overtime;
    }

    public Overtime approvManager(Integer id) {
        getById(id); // method getById
        Overtime overtime = getById(id);
        overtime.setStatus(statusService.getByName("approv by manager"));
        overtime = overtimeRepository.save(overtime);
        HistoryOvertime historyOvertime = new HistoryOvertime();
        historyOvertime.setDate_time(LocalDateTime.now());
        historyOvertime.setStatus(overtime.getStatus().getName());
        historyOvertime.setOvertime(overtime);
        historyOvertime.setDescription("approv");
        historyOvertimeService.create(historyOvertime);
        return overtime;
    }

    public Overtime rejectManager(Integer id, String description) {
        getById(id); // method getById
        Overtime overtime = getById(id);
        overtime.setStatus(statusService.getByName("reject by manager"));
        overtime = overtimeRepository.save(overtime);
        HistoryOvertime historyOvertime = new HistoryOvertime();
        historyOvertime.setDate_time(LocalDateTime.now());
        historyOvertime.setStatus(overtime.getStatus().getName());
        historyOvertime.setOvertime(overtime);
        historyOvertime.setDescription(description);
        historyOvertimeService.create(historyOvertime);
        return overtime;
    }

    public Overtime approvHr(Integer id) {
        getById(id); // method getById
        Overtime overtime = getById(id);
        overtime.setStatus(statusService.getByName("approv by hr"));
        overtime = overtimeRepository.save(overtime);
        HistoryOvertime historyOvertime = new HistoryOvertime();
        historyOvertime.setDate_time(LocalDateTime.now());
        historyOvertime.setStatus(overtime.getStatus().getName());
        historyOvertime.setOvertime(overtime);
        historyOvertime.setDescription("approv");
        historyOvertimeService.create(historyOvertime);
        EmployeeProject employeeProject = overtime.getEmployeeProject();
        Project project = employeeProject.getProject();
        int newBudget = (int) (project.getBudget() - (overtime.getNominal()));
        project.setBudget(newBudget);
        projectService.update(project.getId(), project);
        Employee employee = employeeProject.getEmployee();
        int newPayroll = (int) (employee.getPayroll() + (overtime.getNominal()));
        employee.setPayroll(newPayroll);
        employeeService.update(employee.getId(), employee);
        return overtime;
    }

    public Overtime rejectHr(Integer id, String description) {
        getById(id); // method getById
        Overtime overtime = getById(id);
        overtime.setStatus(statusService.getByName("reject by hr"));
        overtime = overtimeRepository.save(overtime);
        HistoryOvertime historyOvertime = new HistoryOvertime();
        historyOvertime.setDate_time(LocalDateTime.now());
        historyOvertime.setStatus(overtime.getStatus().getName());
        historyOvertime.setOvertime(overtime);
        historyOvertime.setDescription(description);
        historyOvertimeService.create(historyOvertime);
        return overtime;
    }

    public Overtime paid(Integer id) {
        getById(id); // method getById
        Overtime overtime = getById(id);
        overtime.setStatus(statusService.getByName("paid"));
        overtime = overtimeRepository.save(overtime);
        HistoryOvertime historyOvertime = new HistoryOvertime();
        historyOvertime.setDate_time(LocalDateTime.now());
        historyOvertime.setStatus(overtime.getStatus().getName());
        historyOvertime.setOvertime(overtime);
        historyOvertime.setDescription("paid");
        historyOvertimeService.create(historyOvertime);
        EmployeeProject employeeProject = overtime.getEmployeeProject();
        Employee employee = employeeProject.getEmployee();
        int newPayroll = (int) (employee.getPayroll() - (overtime.getNominal()));
        employee.setPayroll(newPayroll);
        employeeService.update(employee.getId(), employee);
        emailService.sendMailOvertimePaid(overtime);
        return overtime;
    }

    public Overtime delete(Integer id) {
        Overtime overtime = getById(id);
        overtimeRepository.delete(overtime);
        return overtime;
    }
}
