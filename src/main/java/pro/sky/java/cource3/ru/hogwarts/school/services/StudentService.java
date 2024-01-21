package pro.sky.java.cource3.ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;
import pro.sky.java.cource3.ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {
        studentRepository.save(student);
        return student;
    }

    public Student delete(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return student.get();
        }
        return null;
    }

    public Student update(Student student) {
        Long id = student.getId();
        Optional<Student> student1 = studentRepository.findById(id);
        if (student1.isPresent()) {
            studentRepository.save(student);
            return student1.get();
        }
        return null;
    }

    public Student read(long id) {
        Student student = studentRepository.getReferenceById(id);
        return student;
    }

    public List<Student> getAll() {
        List<Student> stList = studentRepository.findAll();
        return stList;
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int ageMin, int ageMax) {
        if (ageMin < ageMax) {
            return studentRepository.findByAgeBetween(ageMin, ageMax);
        } else {
            return null;
        }
    }

    public Student findById(Long studentId) {
        return null;
    }
}
