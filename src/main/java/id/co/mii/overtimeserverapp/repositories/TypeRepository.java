package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Type ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.