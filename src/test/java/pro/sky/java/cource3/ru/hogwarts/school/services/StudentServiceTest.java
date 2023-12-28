package pro.sky.java.cource3.ru.hogwarts.school.services;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;

    @Before
    public void setUp() {
        studentService = new StudentService();
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student(1L, "Doe", 20);
        Student createdStudent = studentService.createStudent(student);

        assertNotNull(createdStudent);
        assertNotNull(createdStudent.getId());
        assertEquals(student.getAge(), createdStudent.getAge());
    }

    @Test
    public void testFindStudent() {
        Student student = new Student(1L, "Doe", 20);
        Student createdStudent = studentService.createStudent(student);

        Student foundStudent = studentService.findStudent(createdStudent.getId());

        assertNotNull(foundStudent);
        assertEquals(createdStudent.getId(), foundStudent.getId());
        assertEquals(createdStudent.getAge(), foundStudent.getAge());
    }

    @Test
    public void testEditStudent() {
        Student student = new Student(1L, "Doe", 20);
        Student createdStudent = studentService.createStudent(student);
        Student editedStudent = studentService.editStudent(createdStudent);
        assertNotNull(editedStudent);
        assertEquals(createdStudent.getId(), editedStudent.getId());
        assertEquals(createdStudent.getAge(), editedStudent.getAge());
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student(1L, "Doe", 20);
        Student createdStudent = studentService.createStudent(student);

        Student deletedStudent = studentService.deletStudent(createdStudent.getId());

        assertNotNull(deletedStudent);
        assertEquals(createdStudent.getId(), deletedStudent.getId());
        assertEquals(createdStudent.getAge(), deletedStudent.getAge());

        Student foundStudent = studentService.findStudent(createdStudent.getId());
        assertNull(foundStudent);
    }

    @Test
    public void testFindByAge() {
        Student student1 = new Student(1L, "Doe", 20);
        Student student2 = new Student(2L, "Smith", 25);
        Student student3 = new Student(3L, "Johnson", 20);

        studentService.createStudent(student1);
        studentService.createStudent(student2);
        studentService.createStudent(student3);

        List<Student> foundStudents = studentService.findByAge(20);

        assertEquals(2, foundStudents.size());
        assertTrue(foundStudents.contains(student1));
        assertFalse(foundStudents.contains(student2));
        assertTrue(foundStudents.contains(student3));
    }

}