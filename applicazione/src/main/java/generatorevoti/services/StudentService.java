package generatorevoti.services;

import generatorevoti.database.entities.StudentEntity;
import generatorevoti.database.repositories.StudentRepository;
import generatorevoti.exceptions.StudentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    public String register(StudentEntity student) {
        if (studentRepository.existsById(student.getEmail())){
            throw new StudentException("This student is already registered.");
        }
        studentRepository.save(student);
        return String.format("Student %s %s successfully registered.", student.getName(), student.getSurname());
    }

    public String delete(String email){
        StudentEntity student = studentRepository.findById(email).orElseThrow(() -> new StudentException("This student does not exists."));
        studentRepository.delete(student);
        return String.format("Student %s %s successfully deleted.", student.getName(), student.getSurname());
    }

    public String saveAll(List<StudentEntity> students){
        studentRepository.saveAll(students);
        return "All students have been correctly registered.";
    }

    public List<StudentEntity> findByClazz(String clazz) {
        return studentRepository.findByClazz(clazz);
    }
}