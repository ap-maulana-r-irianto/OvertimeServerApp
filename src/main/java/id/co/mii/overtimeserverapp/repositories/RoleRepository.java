package id.co.mii.overtimeserverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.overtimeserverapp.models.Role;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
}
