package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Status;
import id.co.mii.overtimeserverapp.repositories.StatusRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatusService {
    
    private StatusRepository statusRepository;

    //method untuk menampilkan semua data Status
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    //method untuk menampilkan data Status berdasarkan id
    public Status getById(Integer id) {
        return statusRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Status not found!!"));
    }

    public Status getByName(String name) {
        return statusRepository.findByName(name);
    }

    //method untuk menambah data Status
    public Status create(Status status) {
        return statusRepository.save(status);
    }

    //method untuk mengubah data Status
    public Status update(Integer id, Status status) {
        getById(id); // method getById
        status.setId(id);
        return statusRepository.save(status);
    }

    //method untuk menghapus data Status
    public Status delete(Integer id) {
        Status status = getById(id);
        statusRepository.delete(status);
        return status;
    }
}
