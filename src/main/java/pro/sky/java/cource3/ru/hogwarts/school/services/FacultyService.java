package pro.sky.java.cource3.ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import pro.sky.java.cource3.ru.hogwarts.school.model.Faculty;
import pro.sky.java.cource3.ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> filterByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }
}
