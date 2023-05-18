package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Privilege ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.