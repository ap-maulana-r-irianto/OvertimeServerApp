package id.co.mii.overtimeserverapp.repositories;

import id.co.mii.overtimeserverapp.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}

//digunakan untuk mengelola akses dan Pemrosesan data Employee ke dalam basis data.
//bertanggung jawab menyediakan antarmuka yang memungkinkan melakukan CRUD.