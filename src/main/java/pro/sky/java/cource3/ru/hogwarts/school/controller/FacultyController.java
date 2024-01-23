package pro.sky.java.cource3.ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.cource3.ru.hogwarts.school.model.Faculty;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;
import pro.sky.java.cource3.ru.hogwarts.school.services.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> findStudent(@PathVariable long id) {
        Faculty foundedFaculty = facultyService.findFaculty(id);
        if (foundedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundedFaculty);
    }

    @PostMapping
    public Faculty createStudent(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editStudent(@RequestBody Faculty faculty) {
        Faculty editedFaculty = facultyService.updateFaculty(faculty);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByColorOrName")
    public ResponseEntity<Faculty> findFacultyByColorOrName(@RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByName(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/studentsOfFaculty/{facultyId}")
    public ResponseEntity<Collection<Student>> getStudentsOfFaculty(@PathVariable long facultyId) {
        Collection<Student> students = facultyService.getStudentsOfFaculty(facultyId);
        if (!students.isEmpty()) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/colorFilter/{color}")
    public Collection<Faculty> colorFilteredFaculties(@PathVariable String color) {
        return facultyService.filterColor(color);
    }
}
