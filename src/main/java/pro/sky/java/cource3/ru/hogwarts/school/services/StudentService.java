package pro.sky.java.cource3.ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
@Service
public class StudentService {
    private static final HashMap<Long, Student> students = new HashMap<>();


    public Collection<Student> getAllStudents() {
        return students.values();
    }

    private static long lastId = 0;

    public static Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);

    }

    public static Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student deletStudent(long id) {
        return students.remove(id);
    }


    public List<Student> findByAge(int age) {
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if(student.getAge()==age){
                result.add(student);
            }
        }
        return result;
    }
}
