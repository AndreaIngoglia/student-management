package generatorevoti.controllers;

import com.google.gson.Gson;
import generatorevoti.database.entities.Valutation;
import generatorevoti.services.StudentService;
import generatorevoti.services.ValutationService;
import generatorevoti.utils.ValutationInformation;
import generatorevoti.utils.ValutationPanelInformation;
import generatorevoti.utils.ValutationVisualization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@AllArgsConstructor
public class ViewsController {
    private StudentService studentService;
    private ValutationService valutationService;

    @GetMapping(path = {"/"})
    public String getMainPage(Model model){
        model.addAttribute("userForm" , new ValutationPanelInformation());
        return "index";
    }
    @GetMapping(path = {"/valutationpanel"})
    public String  getValutationInputPanel(@ModelAttribute ValutationPanelInformation panelInformation, Model model) {
        model.addAttribute("userForm", new ValutationInformation());
        model.addAttribute("jsonResults", new ValutationVisualization());
        model.addAttribute("subject", panelInformation.getSubject());
        model.addAttribute("date", panelInformation.getDate());
        model.addAttribute("academicYear", panelInformation.getAcademicYear());
        model.addAttribute("students", studentService.findByClazzAndAcademicYear(panelInformation.getClazz(), panelInformation.getAcademicYear()));
        return "valutationpanel";
    }
    @GetMapping(path = "/jsonvalutation")
    public String getJsonValutation(@ModelAttribute ValutationVisualization vv, Model model){
        List<Valutation> valutations = valutationService.findByClazzAndSubjectAndDateAndAcademicYear(vv.getClazz(), vv.getSubject(), vv.getDate(), vv.getAcademicYear());
        String jsonValutations = new Gson().toJson(valutations);
        model.addAttribute("valutations" , jsonValutations);
        return "valutations";
    }
}
