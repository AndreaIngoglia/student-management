package generatorevoti.controllers;

import generatorevoti.database.entities.StudentEntity;
import generatorevoti.exceptions.StudentException;
import generatorevoti.services.StudentService;
import generatorevoti.utils.StudentInformation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @PostMapping(path = {"/students/{email}"})
    public String register(@PathVariable String email, @RequestBody StudentInformation input) {
        return studentService.register(map(email, input));
    }

    @DeleteMapping(path = {"/students/{email}"})
    public String delete(@PathVariable String email){
        return studentService.delete(email);
    }

    @PostMapping(path = {"/students"})
    public String saveAll(@RequestBody List<StudentEntity> students){
        return studentService.saveAll(students);
    }

    private StudentEntity map(String email, StudentInformation input){
        return new StudentEntity(email, input.getName(), input.getSurname(), input.getClazz());
    }

    @ExceptionHandler(StudentException.class)
    public String exceptionHandler(StudentException e){
        return e.getError();
    }
}