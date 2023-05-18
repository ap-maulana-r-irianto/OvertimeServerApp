package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.HistoryReimburse;

@Repository
public interface HistoryReimburseRepository extends JpaRepository<HistoryReimburse, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data History Reimburse ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.