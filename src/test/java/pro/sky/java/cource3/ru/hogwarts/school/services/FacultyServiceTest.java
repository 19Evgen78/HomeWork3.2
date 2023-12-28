package pro.sky.java.cource3.ru.hogwarts.school.services;

import org.junit.jupiter.api.Test;
import pro.sky.java.cource3.ru.hogwarts.school.model.Faculty;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {
    @Test
    public void testAddFaculty() {
        FacultyService facultyService = new FacultyService();

        Faculty faculty = new Faculty(1, "hogwart","red");

        facultyService.addFaculty(faculty);
        assertEquals(faculty, facultyService.findFaculty(faculty.getId()));
    }

    @Test
    public void testFindFaculty() {
        FacultyService facultyService = new FacultyService();

        Faculty faculty = new Faculty(1, "hogwart","red");

        facultyService.addFaculty(faculty);
        assertEquals(faculty, facultyService.findFaculty(faculty.getId()));
    }

    @Test
    public void testEditFaculty() {
        FacultyService facultyService = new FacultyService();

        Faculty faculty = new Faculty(1, "hogwart","red");

        facultyService.addFaculty(faculty);
        faculty.setColor("blue");

        Faculty editedFaculty = facultyService.editFaculty(faculty);
        assertEquals(editedFaculty, facultyService.findFaculty(faculty.getId()));
        assertEquals("blue", editedFaculty.getColor());
    }

    @Test
    public void testDeleteFaculty() {
        FacultyService facultyService = new FacultyService();

        Faculty faculty = new Faculty(1, "hogwart","red");

        facultyService.addFaculty(faculty);
        facultyService.deleteFaculty(faculty.getId());
        assertNull(facultyService.findFaculty(faculty.getId()));
    }

    @Test
    public void testFindByColor() {
        FacultyService facultyService = new FacultyService();

        Faculty faculty1 = new Faculty(1, "hogwart","red");
        Faculty faculty2 = new Faculty(2,"math","blue");
        Faculty faculty3 = new Faculty(3,"phizikc","red");

        facultyService.addFaculty(faculty1);
        facultyService.addFaculty(faculty2);
        facultyService.addFaculty(faculty3);

        Collection<Faculty> faculties = facultyService.findByColor("red");

        assertEquals(2, faculties.size());
        assertTrue(faculties.contains(faculty1));
        assertTrue(faculties.contains(faculty3));
    }
}