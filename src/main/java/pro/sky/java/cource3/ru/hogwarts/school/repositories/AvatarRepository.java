package pro.sky.java.cource3.ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.cource3.ru.hogwarts.school.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long>{
    Avatar findByStudentId(Long student_id);
}
