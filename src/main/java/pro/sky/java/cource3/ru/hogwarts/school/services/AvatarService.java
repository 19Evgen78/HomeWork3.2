package pro.sky.java.cource3.ru.hogwarts.school.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.java.cource3.ru.hogwarts.school.model.Avatar;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;
import pro.sky.java.cource3.ru.hogwarts.school.repositories.AvatarRepository;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${students.avatars.dir.path}")
    private String avatarDir;
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }
    public void uploadAvatar(Long studentId, MultipartFile file)throws IOException {
        Student student = studentService.findById(studentId);
        Path filePath = Path.of(avatarDir,student  + "."
                + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (BufferedInputStream bis = new BufferedInputStream
                (file.getInputStream(),1024);
             BufferedOutputStream bos = new BufferedOutputStream
                     (Files.newOutputStream(filePath,CREATE_NEW),1024);
        ){
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(String.valueOf(filePath));
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setAvatar(file.getBytes());
        avatarRepository.save(avatar);
    }
    public Avatar findAvatar(Long student_id){
        if (avatarRepository.findByStudentId(student_id) != null){
            return avatarRepository.findByStudentId(student_id);}
        return new Avatar();
    }
    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
