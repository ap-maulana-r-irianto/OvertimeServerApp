package id.co.mii.overtimeserverapp.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import id.co.mii.overtimeserverapp.exceptions.FileStorageException;
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
    private ModelMapper modelMapper;

    public List<Reimburse> getAll() {
        return reimburseRepository.findAll();
    }

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

    public Reimburse create(ReimburseRequest reimburseRequest) {
        Reimburse reimburse = modelMapper.map(reimburseRequest, Reimburse.class);
        reimburse.setDate_time(LocalDateTime.now());
        reimburse.setStatus("pending");
        reimburse.setEmployee(employeeService.getById(reimburseRequest.getEmployee_id()));
        reimburse.setType(typeService.getById(reimburseRequest.getType_id()));
        return reimburseRepository.save(reimburse);
    }

    public Reimburse update(Integer id, Reimburse reimburse) {
        getById(id); // method getById
        reimburse.setId(id);
        return reimburseRepository.save(reimburse);
    }

    public Reimburse delete(Integer id) {
        Reimburse reimburse = getById(id);
        reimburseRepository.delete(reimburse);
        return reimburse;
    }

}
