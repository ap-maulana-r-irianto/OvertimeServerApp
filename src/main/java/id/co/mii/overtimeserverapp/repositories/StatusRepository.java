package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Status ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.