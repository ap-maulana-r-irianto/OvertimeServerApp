package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Privilege;
import id.co.mii.overtimeserverapp.repositories.PrivilegeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    //method untuk Menampilkan semua data dari Privilege
    public List<Privilege> getAll() {
        return privilegeRepository.findAll();
    }

    //method untuk Menampilkan data privilege berdasarkan id
    public Privilege getById(Integer id) {
        return privilegeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Privilege not found!!"));
    }

    //method untuk (create) menambahkan data Privilege
    public Privilege create(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    //method untuk (update) mengubah data privilege berdasarkan id
    public Privilege update(Integer id, Privilege privilege) {
        getById(id); // method getById
        privilege.setId(id);
        return privilegeRepository.save(privilege);
    }

    //method untuk (delete) menghapus data privilege berdasarkan id
    public Privilege delete(Integer id) {
        Privilege privilege = getById(id);
        privilegeRepository.delete(privilege);
        return privilege;
    }
}
