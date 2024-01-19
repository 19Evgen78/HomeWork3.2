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

    public Faculty create(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty delete(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            facultyRepository.deleteById(id);
            return faculty.get();
        }
        return null;
    }

    public Faculty update(Faculty faculty) {
        Long id = faculty.getId();
        Optional<Faculty> faculty1 = facultyRepository.findById(id);
        if (faculty1.isPresent()) {
            facultyRepository.save(faculty);
            return faculty1.get();
        }
        return null;
    }

    public Faculty read(long id) {
        Faculty faculty = facultyRepository.getReferenceById(id);
        return faculty;

    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }


    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByColorOrNameIgnoreCase(String color, String name) {
        return facultyRepository.findByColorOrNameIgnoreCase(color,name);
    }
}
