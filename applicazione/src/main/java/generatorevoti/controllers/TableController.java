package generatorevoti.controllers;

import generatorevoti.database.entities.Student;
import generatorevoti.services.TableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TableController {
    private TableService tableService;

    @PostMapping(path = {"/students"})
    public String saveAll(@RequestBody List<Student> students){
        return tableService.saveAll(students);
    }
}
