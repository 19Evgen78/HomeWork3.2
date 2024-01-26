package pro.sky.java.cource3.ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.java.cource3.ru.hogwarts.school.model.Student;
import pro.sky.java.cource3.ru.hogwarts.school.repositories.StudentRepository;
import pro.sky.java.cource3.ru.hogwarts.school.services.StudentService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    @Test
    void createStudentTest() throws Exception {
        long id = 1L;
        String name = "Ron";
        int age = 17;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);

        Student student = new Student();
        student.setAge(age);
        student.setName(name);
        student.setId(id);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.age").value(age));

        verify(studentRepository, times(1)).save(argThat(savedStudent ->
                savedStudent.getName().equals(name) && savedStudent.getAge() == age
        ));
    }

    @Test
    void getStudentTest() throws Exception {
        Long id = 1L;
        String name = "Ron";
        int age = 17;

        Student student = new Student(id, name, age);

        when(studentRepository.findStudentById(id)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(status().isOk());
    }

    @Test
    void updateStudentTest() throws Exception {
        Long id = 1L;
        String name = "Ron";
        int age = 13;
        int newAge = 18;

        Student student = new Student(id, name, age);
        Student updatedStudent = new Student(id, name, newAge);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("age", newAge);

        when(studentService.updateStudent(eq(id), any(Student.class))).thenReturn(updatedStudent);
        when(studentRepository.findStudentById(id)).thenReturn(updatedStudent);
        when(studentRepository.save(student)).thenReturn(updatedStudent);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/update/" + id)
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(newAge));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        long id = 1L;
        String name = "Bob";
        int age = 37;
        Student student = new Student(id, name, age);

        when(studentRepository.findStudentById(id)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}