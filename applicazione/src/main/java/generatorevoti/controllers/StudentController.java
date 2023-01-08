package generatorevoti.controllers;

import generatorevoti.services.StudentService;
import generatorevoti.database.entities.Student;
import generatorevoti.exceptions.StudentException;
import generatorevoti.utils.Information;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @PostMapping(path = {"/students/{email}"})
    public String register(@PathVariable String email, @RequestBody Information input) {
        return studentService.register(map(email, input));
    }

    @DeleteMapping(path = {"/students/{email}"})
    public String delete(@PathVariable String email){
        return studentService.delete(email);
    }

    @GetMapping(path = {"/students"})
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @PostMapping(path = {"/students"})
    public String saveAll(@RequestBody List<Student> students){
        return studentService.saveAll(students);
    }

    private Student map(String email, Information input){
        return new Student(email, input.getName(), input.getSurname(), input.getClazz(), input.getAcademicYear());
    }

    @ExceptionHandler(StudentException.class)
    public String exceptionHandler(StudentException e){
        return e.getError();
    }
}