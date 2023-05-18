package id.co.mii.overtimeserverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.overtimeserverapp.models.Project;
import id.co.mii.overtimeserverapp.repositories.ProjectRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;

    //method untuk menampilkan semua data Project
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    //method untuk menampilkan data Project berdasarkan id
    public Project getById(Integer id) {
        return projectRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Project not found!!"));
    }

    //method untuk menambah data Project
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    //method untuk mengubah data Project
    public Project update(Integer id, Project project) {
        getById(id); // method getById
        project.setId(id);
        return projectRepository.save(project);
    }

    //method untuk menghapus data Project
    public Project delete(Integer id) {
        Project project = getById(id);
        projectRepository.delete(project);
        return project;
    }
    
}
