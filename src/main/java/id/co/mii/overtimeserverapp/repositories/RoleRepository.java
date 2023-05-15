package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Role ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.