package pro.sky.java.cource3.ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int ageMin, int ageMax);
}
