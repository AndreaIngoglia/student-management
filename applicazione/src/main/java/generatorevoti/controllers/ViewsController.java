package generatorevoti.controllers;

import generatorevoti.services.StudentService;
import generatorevoti.utils.ValutationInformation;
import generatorevoti.utils.ValutationPanelInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class ViewsController {
    private StudentService studentService;

    @GetMapping(path = {"/"})
    public String getMainPage(Model model){
        model.addAttribute("userForm" , new ValutationPanelInformation());
        return "index";
    }
    @GetMapping(path = {"/valutationpanel"})
    public String  getValutationInputPanel(@ModelAttribute ValutationPanelInformation panelInformation, Model model) {
        model.addAttribute("userForm", new ValutationInformation());
        model.addAttribute("subject", panelInformation.getSubject());
        model.addAttribute("date", panelInformation.getDate());
        model.addAttribute("students", studentService.findByClazzAndAcademicYear(panelInformation.getClazz(), panelInformation.getAcademicYear()));
        return "valutationpanel";
    }
}
