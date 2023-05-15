package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Department ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.