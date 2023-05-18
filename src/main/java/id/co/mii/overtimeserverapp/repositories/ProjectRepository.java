package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Project ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.