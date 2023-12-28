package pro.sky.java.cource3.ru.hogwarts.school.services;

import org.junit.jupiter.api.Test;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @Test
    public void testAddStudent() {
        StudentService studentService = new StudentService();

        Student student = new Student(1, "Harry", 20);
        studentService.addStudent(student);

        assertEquals(student, studentService.findStudent(student.getId()));
    }

    @Test
    public void testFindStudent() {
        StudentService studentService = new StudentService();

        Student student = new Student(1, "Harry", 20);
        studentService.addStudent(student);

        assertEquals(student, studentService.findStudent(student.getId()));
    }

    @Test
    public void testEditStudent() {
        StudentService studentService = new StudentService();

        Student student = new Student(1, "Harry", 20);
        studentService.addStudent(student);

        student.setName("Ron");
        Student editedStudent = studentService.editStudent(student);

        assertEquals(editedStudent, studentService.findStudent(student.getId()));
        assertEquals("Ron", editedStudent.getName());
    }

    @Test
    public void testDeleteStudent() {
        StudentService studentService = new StudentService();

        Student student = new Student(1, "Harry", 20);
        studentService.addStudent(student);

        studentService.deleteStudent(student.getId());

        assertNull(studentService.findStudent(student.getId()));
    }

    @Test
    public void testFindByAge() {
        StudentService studentService = new StudentService();

        Student student1 = new Student(1, "Harry", 20);
        Student student2 = new Student(2, "Ron", 18);
        Student student3 = new Student(3, "Germiona", 20);
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        Collection<Student> students = studentService.findByAge(20);

        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student3));
    }
}