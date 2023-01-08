package generatorevoti.controllers;

import generatorevoti.services.StudentService;
import generatorevoti.utils.ValutationInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PanelRegistrationController {
    private StudentService studentService;
    @GetMapping(path = {"/table"})
    public String  getValutationInputPanel(@RequestParam String subject, @RequestParam String date, Model model) {
        model.addAttribute("userForm", new ValutationInformation());
        model.addAttribute("subject", subject);
        model.addAttribute("date", date);
        model.addAttribute("students", studentService.findAll());
        return "index";
    }
}
