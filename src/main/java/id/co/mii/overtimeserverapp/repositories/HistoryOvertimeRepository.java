package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.HistoryOvertime;

@Repository
public interface HistoryOvertimeRepository extends JpaRepository<HistoryOvertime, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data History Overtime ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.