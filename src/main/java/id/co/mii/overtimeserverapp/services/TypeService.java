package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Type;
import id.co.mii.overtimeserverapp.repositories.TypeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TypeService {
    
    private TypeRepository typeRepository;

    //method untuk menampilkan semua data Type
    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    //method untuk menampilkan data Type berdasarkan id
    public Type getById(Integer id) {
        return typeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Role not found!!"));
    }

    //method untuk (create) menambahkan data Type
    public Type create(Type type) {
        return typeRepository.save(type);
    }

    //method untuk (update) mengubah data Type
    public Type update(Integer id, Type type) {
        getById(id); // method getById
        type.setId(id);
        return typeRepository.save(type);
    }

    //method untuk (delete) menghapus data Type
    public Type delete(Integer id) {
        Type type = getById(id);
        typeRepository.delete(type);
        return type;
    }
}
