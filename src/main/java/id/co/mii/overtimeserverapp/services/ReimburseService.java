package id.co.mii.overtimeserverapp.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.Multipart;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import id.co.mii.overtimeserverapp.models.Employee;
import id.co.mii.overtimeserverapp.models.HistoryReimburse;
import id.co.mii.overtimeserverapp.models.Reimburse;
import id.co.mii.overtimeserverapp.models.dto.requests.ReimburseRequest;
import id.co.mii.overtimeserverapp.repositories.ReimburseRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReimburseService {

    private ReimburseRepository reimburseRepository;
    private EmployeeService employeeService;
    private TypeService typeService;
    private StatusService statusService;
    private HistoryReimburseService historyReimburseService;
    private FileStorageService fileStorageService;
    private EmailService emailService;
    private ModelMapper modelMapper;

    //method untuk menampilkan semua data Reimburse
    public List<Reimburse> getAll() {
        return reimburseRepository.findAll();
    }

    //method untuk menampilkan data Reimburse berdasarkan id
    public Reimburse getById(Integer id) {
        return reimburseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Reimburse not found!!"));
    }

    // public Reimburse create(Reimburse reimburse) {
    // return reimburseRepository.save(reimburse);
    // }

    //method untuk (create) menambahkan data Reimburse
    public Reimburse create(ReimburseRequest reimburseRequest, MultipartFile file) {
        Reimburse reimburse = modelMapper.map(reimburseRequest, Reimburse.class);
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        reimburse.setFile_url(fileDownloadUri);
        reimburse.setDate_time(LocalDateTime.now());
        reimburse.setStatus(statusService.getById(1));
        reimburse.setEmployee(employeeService.getById(reimburseRequest.getEmployee_id()));
        reimburse.setType(typeService.getById(reimburseRequest.getType_id()));
        reimburse = reimburseRepository.save(reimburse);
        HistoryReimburse historyReimburse = new HistoryReimburse();
        historyReimburse.setDate_time(LocalDateTime.now());
        historyReimburse.setStatus(reimburse.getStatus().getName());
        historyReimburse.setReimburse(reimburse);
        historyReimburse.setDescription("username");
        historyReimburseService.create(historyReimburse);
        return reimburse;
    }

    //method untuk (update) mengubah data Reimburse
    public Reimburse update(Integer id, Reimburse reimburse, MultipartFile file) {
        getById(id); // method getById
        reimburse.setId(id);
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        reimburse.setFile_url(fileDownloadUri);
        reimburse.setDate_time(LocalDateTime.now());
        reimburse.setType(reimburse.getType());
        reimburse = reimburseRepository.save(reimburse);
        HistoryReimburse historyReimburse = new HistoryReimburse();
        historyReimburse.setDate_time(LocalDateTime.now());
        historyReimburse.setStatus(reimburse.getStatus().getName());
        historyReimburse.setReimburse(reimburse);
        historyReimburse.setDescription(reimburse.getDescription());
        historyReimburseService.create(historyReimburse);
        return reimburse;
    }

    public Reimburse approvManager(Integer id) {
        getById(id); // method getById
        Reimburse reimburse = getById(id);
        reimburse.setStatus(statusService.getByName("approv by manager"));
        reimburse = reimburseRepository.save(reimburse);
        HistoryReimburse historyReimburse = new HistoryReimburse();
        historyReimburse.setDate_time(LocalDateTime.now());
        historyReimburse.setStatus(reimburse.getStatus().getName());
        historyReimburse.setReimburse(reimburse);
        historyReimburse.setDescription("approv");
        historyReimburseService.create(historyReimburse);
        return reimburse;
    }

    public Reimburse rejectManager(Integer id, String description) {
        getById(id); // method getById
        Reimburse reimburse = getById(id);
        reimburse.setStatus(statusService.getByName("reject by manager"));
        reimburse = reimburseRepository.save(reimburse);
        HistoryReimburse historyReimburse = new HistoryReimburse();
        historyReimburse.setDate_time(LocalDateTime.now());
        historyReimburse.setStatus(reimburse.getStatus().getName());
        historyReimburse.setReimburse(reimburse);
        historyReimburse.setDescription(description);
        historyReimburseService.create(historyReimburse);
        return reimburse;
    }

    public Reimburse approvHr(Integer id) {
        getById(id); // method getById
        Reimburse reimburse = getById(id);
        reimburse.setStatus(statusService.getByName("approv by hr"));
        reimburse = reimburseRepository.save(reimburse);
        HistoryReimburse historyReimburse = new HistoryReimburse();
        historyReimburse.setDate_time(LocalDateTime.now());
        historyReimburse.setStatus(reimburse.getStatus().getName());
        historyReimburse.setReimburse(reimburse);
        historyReimburse.setDescription("approv");
        historyReimburseService.create(historyReimburse);
        Employee employee = reimburse.getEmployee();
        int newPayroll = (int) (employee.getPayroll() + reimburse.getNominal());
        employee.setPayroll(newPayroll);
        employeeService.update(employee.getId(), employee);
        return reimburse;
    }

    public Reimburse rejectHr(Integer id, String description) {
        getById(id); // method getById
        Reimburse reimburse = getById(id);
        reimburse.setStatus(statusService.getByName("reject by hr"));
        reimburse = reimburseRepository.save(reimburse);
        HistoryReimburse historyReimburse = new HistoryReimburse();
        historyReimburse.setDate_time(LocalDateTime.now());
        historyReimburse.setStatus(reimburse.getStatus().getName());
        historyReimburse.setReimburse(reimburse);
        historyReimburse.setDescription(description);
        historyReimburseService.create(historyReimburse);
        return reimburse;
    }

    public Reimburse paid(Integer id) {
        getById(id); // method getById
        Reimburse reimburse = getById(id);
        reimburse.setStatus(statusService.getByName("paid"));
        reimburse = reimburseRepository.save(reimburse);
        HistoryReimburse historyReimburse = new HistoryReimburse();
        historyReimburse.setDate_time(LocalDateTime.now());
        historyReimburse.setStatus(reimburse.getStatus().getName());
        historyReimburse.setReimburse(reimburse);
        historyReimburse.setDescription("paid");
        historyReimburseService.create(historyReimburse);
        Employee employee = reimburse.getEmployee();
        int newPayroll = (int) (employee.getPayroll() - reimburse.getNominal());
        employee.setPayroll(newPayroll);
        employeeService.update(employee.getId(), employee);
        emailService.sendMailReimbursePaid(reimburse);
        return reimburse;
    }

    //method untuk (delete) menghapus data Reimburse
    public Reimburse delete(Integer id) {
        Reimburse reimburse = getById(id);
        reimburseRepository.delete(reimburse);
        return reimburse;
    }

}
