package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Role;
import id.co.mii.overtimeserverapp.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    
    private RoleRepository roleRepository;

    //method untuk menampilkan semua data Role
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    //method untuk menampilkan data Role berdasarkan id
    public Role getById(Integer id) {
        return roleRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Role not found!!"));
    }

    //method untuk menambah data Role
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    //method untuk mengubah data Role
    public Role update(Integer id, Role role) {
        getById(id); // method getById
        role.setId(id);
        return roleRepository.save(role);
    }

    //method untuk menghapus data Role
    public Role delete(Integer id) {
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}
