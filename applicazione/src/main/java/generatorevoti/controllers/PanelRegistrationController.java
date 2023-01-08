package generatorevoti.controllers;

import generatorevoti.database.entities.Valutation;
import generatorevoti.database.entities.ValutationId;
import generatorevoti.database.repositories.ValutationDao;
import generatorevoti.services.StudentService;
import generatorevoti.utils.ValutationInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class PanelRegistrationController {
    private StudentService studentService;
    private ValutationDao valutationDao;
    @GetMapping(path = {"/table"})
    public String  greetings(@RequestParam String subject, @RequestParam String date, Model model){
        model.addAttribute("userForm", new ValutationInformation());
        model.addAttribute("subject", subject);
        model.addAttribute("date", date);
        model.addAttribute("students" , studentService.findAll());
        return "index";
    }

    @PostMapping(path = {"/register"})
    public String register(@ModelAttribute ValutationInformation information){
        valutationDao.save(map(information));
        return "index";
    }

    private Valutation map(ValutationInformation input){
        return new Valutation(new ValutationId(input.getDate(), input.getSubject(), input.getEmail()), input.getName(), input.getSurname(), input.getMark());
    }
}