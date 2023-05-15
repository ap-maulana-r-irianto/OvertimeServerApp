package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.EmployeeProject;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Employee Project ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.