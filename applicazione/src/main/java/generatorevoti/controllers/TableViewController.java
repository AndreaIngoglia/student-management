package generatorevoti.controllers;

import generatorevoti.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class TableViewController {
    private StudentService studentService;
    @GetMapping(path = {"/table/{subject}/{date}"})
    public String  greetings(@PathVariable String subject, @PathVariable String date, Model model){
        model.addAttribute("subject", subject);
        model.addAttribute("date", date);
        model.addAttribute("students" , studentService.findAll());
        return "index";
    }
}
