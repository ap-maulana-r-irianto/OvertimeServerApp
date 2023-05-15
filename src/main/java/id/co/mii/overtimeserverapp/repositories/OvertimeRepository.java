package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Overtime;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Overtime ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.