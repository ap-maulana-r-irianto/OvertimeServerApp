package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Reimburse;

@Repository
public interface ReimburseRepository extends JpaRepository<Reimburse, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data reimburse ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.