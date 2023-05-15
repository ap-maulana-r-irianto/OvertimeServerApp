package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Employee;
import id.co.mii.overtimeserverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    //method untuk menampilkan semua data Employee
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    //method untuk menampilkan data Employee berdasarkan id
    public Employee getById(Integer id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee not found!!"));
    }

    //method untuk menambahkan data Employee
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    //method untuk mengubah data Employee
    public Employee update(Integer id, Employee employee) {
        getById(id); // method getById
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    //method untuk menghapus data Employee
    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
