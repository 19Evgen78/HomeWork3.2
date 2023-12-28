package pro.sky.java.cource3.ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.cource3.ru.hogwarts.school.model.Faculty;
import pro.sky.java.cource3.ru.hogwarts.school.services.FacultyService;

import java.util.Collection;
import java.util.List;
@RestController
@RequestMapping("facultys")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty getFacultyInfo(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }


    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllfacultys() {
        return ResponseEntity.ok(facultyService.getAllfacultys());
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public Faculty deletFaculty(@PathVariable Long id) {
        return facultyService.deletFaculty(id);
    }

    @GetMapping("/color/{color}")
    public List<Faculty> filterFacultyByColor(@PathVariable String color) {
        return facultyService.findByColor(color);
    }
}
