package pro.sky.java.cource3.ru.hogwarts.school.services;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import pro.sky.java.cource3.ru.hogwarts.school.model.Faculty;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {
    private FacultyService facultyService;

    @Before
    public void setUp() {
        facultyService = new FacultyService();
    }

    @Test
    public void testCreateFaculty() {
        Faculty faculty = new Faculty(1L, "Doe","Blue");
        Faculty createdFaculty = facultyService.createFaculty(faculty);

        assertNotNull(createdFaculty);
        assertNotNull(createdFaculty.getId());
        assertEquals(faculty.getName(), createdFaculty.getName());
        assertEquals(faculty.getColor(), createdFaculty.getColor());
    }

    @Test
    public void testFindFaculty() {
        Faculty faculty = new Faculty(1L, "Doe","Blue");
        Faculty createdFaculty = facultyService.createFaculty(faculty);

        Faculty foundFaculty = facultyService.findFaculty(createdFaculty.getId());

        assertNotNull(foundFaculty);
        assertEquals(createdFaculty.getId(), foundFaculty.getId());
        assertEquals(createdFaculty.getName(), foundFaculty.getName());
        assertEquals(createdFaculty.getColor(), foundFaculty.getColor());
    }

    @Test
    public void testEditFaculty() {
        Faculty faculty = new Faculty(1L, "Doe","Blue");
        Faculty createdFaculty = facultyService.createFaculty(faculty);

        createdFaculty.setColor("Red");

        Faculty editedFaculty = facultyService.editFaculty(createdFaculty);

        assertNotNull(editedFaculty);
        assertEquals(createdFaculty.getId(), editedFaculty.getId());
        assertEquals(createdFaculty.getName(), editedFaculty.getName());
        assertEquals(createdFaculty.getColor(), editedFaculty.getColor());
    }

    @Test
    public void testDeleteFaculty() {
        Faculty faculty = new Faculty(1L, "Doe","Blue");
        Faculty createdFaculty = facultyService.createFaculty(faculty);

        Faculty deletedFaculty = facultyService.deletFaculty(createdFaculty.getId());

        assertNotNull(deletedFaculty);
        assertEquals(createdFaculty.getId(), deletedFaculty.getId());
        assertEquals(createdFaculty.getName(), deletedFaculty.getName());
        assertEquals(createdFaculty.getColor(), deletedFaculty.getColor());

        Faculty foundFaculty = facultyService.findFaculty(createdFaculty.getId());
        assertNull(foundFaculty);
    }

    @Test
    public void testFindByColor() {
        Faculty faculty1 = new Faculty(1L, "Doe","Blue");
        Faculty faculty2 = new Faculty(2L, "Smith","Red");
        Faculty faculty3 = new Faculty(3L, "Johnson","Blue");

        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);

        List<Faculty> foundFaculties = facultyService.findByColor("Blue");

        assertEquals(2, foundFaculties.size());
        assertTrue(foundFaculties.contains(faculty1));
        assertFalse(foundFaculties.contains(faculty2));
        assertTrue(foundFaculties.contains(faculty3));
    }
}