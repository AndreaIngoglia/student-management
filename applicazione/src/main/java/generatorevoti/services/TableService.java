package generatorevoti.services;

import generatorevoti.database.entities.Student;
import generatorevoti.database.repositories.StudentDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TableService {
    private StudentDao studentDao;

    public String saveAll(List<Student> students){
        studentDao.saveAll(students);
        return "All students have been correctly registered.";
    }
}
