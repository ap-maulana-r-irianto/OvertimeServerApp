package id.co.mii.overtimeserverapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameOrEmployee_Email(String username, String email);
}

//digunakan untuk mengelola akses dan Pemrosesan data User ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.