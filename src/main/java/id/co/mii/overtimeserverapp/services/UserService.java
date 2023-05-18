package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Role;
import id.co.mii.overtimeserverapp.models.User;
import id.co.mii.overtimeserverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    //method untuk menampilkan semua data User
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //method untuk menampilkan data User berdasarkan id
    public User getById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Role not found!!"));
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //method untuk (create) menambahkan data User
    public User create(User user) {
        return userRepository.save(user);
    }

    //method untuk (update) mengubah data User
    public User update(Integer id, User user) {
        getById(id); // method getById
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //method untuk (delete) menghapus data User
    public User delete(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }

    //method untuk menambahkan Role User
    public User addRole(Integer id, Role role) {
        User user = getById(id);
        List<Role> roles = user.getRole();
        roles.add(roleService.getById(role.getId()));
        user.setRole(roles);
        return userRepository.save(user);
    }
}
