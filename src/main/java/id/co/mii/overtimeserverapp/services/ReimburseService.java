package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Reimburse;
import id.co.mii.overtimeserverapp.repositories.ReimburseRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReimburseService {
    
    private ReimburseRepository reimburseRepository;

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

    public Reimburse create(Reimburse reimburse) {
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
