package id.co.mii.overtimeserverapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.EmployeeProject;
import id.co.mii.overtimeserverapp.models.Overtime;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {
    
}
