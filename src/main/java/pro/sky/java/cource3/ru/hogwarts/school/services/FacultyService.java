package pro.sky.java.cource3.ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import pro.sky.java.cource3.ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
@Service
public class FacultyService {
    private static final HashMap<Long, Faculty> facultys = new HashMap<>();
    private static long lastId = 0;

    public Collection<Faculty> getAllfacultys() {
        return facultys.values();
    }

    public static Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facultys.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultys.get(id);

    }

    public static Faculty editFaculty(Faculty faculty) {
        facultys.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deletFaculty(long id) {
        return facultys.remove(id);
    }


    public List<Faculty> findByColor(String color) {
        List<Faculty> result = new ArrayList<>();
        for (Faculty faculty:
                facultys.values()) {
            if(faculty.getColor()!=null && faculty.getColor().equals(color)){
                result.add(faculty);
            }
        }
        return result;
    }
}
