package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.HistoryOvertime;

@Repository
public interface HistoryOvertimeRepository extends JpaRepository<HistoryOvertime, Integer>{
    
}
