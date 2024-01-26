package pro.sky.java.cource3.ru.hogwarts.school.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.java.cource3.ru.hogwarts.school.model.Faculty;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;
import pro.sky.java.cource3.ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
    public Faculty findByName(String name) {
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }
    public Faculty findByColor(String color) {
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }
    public Collection<Student> getStudentsOfFaculty(long id) {
        Faculty faculty = facultyRepository.findFacultyById(id);
        return (faculty != null) ? faculty.getStudent() : Collections.emptyList();
    }
    public Collection<Faculty> filterColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
