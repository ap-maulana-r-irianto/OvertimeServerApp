package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Department;
import id.co.mii.overtimeserverapp.models.dto.requests.DepartmentRequest;
import id.co.mii.overtimeserverapp.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
    
    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    //method untuk menampilkan semua data Department 
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    //method untuk menampilkan data Department berdasarkan id
    public Department getById(Integer id) {
        return departmentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Department not found!!"));
    }

    // public Department create(Department department) {
    //     return departmentRepository.save(department);
    // }

    //method untuk (create) menambahkan data Department
    public Department create(DepartmentRequest departmentRequest) {
        Department department = modelMapper.map(departmentRequest, Department.class);
        department.setManager(employeeService.getById(departmentRequest.getManager_id()));
        department.setHr(employeeService.getById(departmentRequest.getHr_id()));
        return departmentRepository.save(department);
    }

    //method untuk (update) mengubah data Department
    public Department update(Integer id, Department department) {
        getById(id); // method getById
        department.setId(id);
        return departmentRepository.save(department);
    }

    //method untuk (delete) menghapus data Department
    public Department delete(Integer id) {
        Department department = getById(id);
        departmentRepository.delete(department);
        return department;
    }
}
