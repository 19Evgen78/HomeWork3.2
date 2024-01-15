package pro.sky.java.cource3.ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.cource3.ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByColor(String color);

    Collection<Faculty> ByColorOrNameIgnoreCase(String colorOrName);
}
