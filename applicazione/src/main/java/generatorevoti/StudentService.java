package generatorevoti;

import generatorevoti.database.entities.Student;
import generatorevoti.database.repositories.StudentDao;
import generatorevoti.excpetions.StudentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentDao studentDao;
    public String register(Student student) {
        if (studentDao.existsById(student.getEmail())){
            throw new StudentException("This student is already registered.");
        }
        studentDao.save(student);
        return registrationResponse(student.getName(), student.getSurname());
    }

    public String delete(String email){
        Student student = studentDao.findById(email).orElseThrow(() -> new StudentException("This student does not exists."));
        studentDao.delete(student);
        return deletionResponse(student.getName(), student.getSurname());
    }

    public List<Student> findAll(){
        return studentDao.findAll();
    }

    private String registrationResponse(String name , String surname){
        return String.format("Student %s %s successfully registered.", name, surname);
    }

    private String deletionResponse(String name , String surname){
        return String.format("Student %s %s successfully deleted.", name, surname);
    }
}
