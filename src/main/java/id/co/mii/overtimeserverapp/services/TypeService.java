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

    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    public Type getById(Integer id) {
        return typeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Role not found!!"));
    }

    public Type create(Type type) {
        return typeRepository.save(type);
    }

    public Type update(Integer id, Type type) {
        getById(id); // method getById
        type.setId(id);
        return typeRepository.save(type);
    }

    public Type delete(Integer id) {
        Type type = getById(id);
        typeRepository.delete(type);
        return type;
    }
}
