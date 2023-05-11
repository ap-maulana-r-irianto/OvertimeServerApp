package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.File;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

}