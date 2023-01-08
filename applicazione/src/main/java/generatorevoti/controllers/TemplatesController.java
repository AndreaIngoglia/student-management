package generatorevoti.controllers;

import generatorevoti.database.entities.Student;
import generatorevoti.services.StudentService;
import generatorevoti.services.ValutationService;
import generatorevoti.utils.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@AllArgsConstructor
public class TemplatesController {
    private StudentService studentService;
    private ValutationService valutationService;

    @GetMapping(path = {"/"})
    public String getMainPage(Model model) {
        model.addAttribute("userForm", new ValutationPanelInformation());
        model.addAttribute("statsInformation", new StatsInformation());
        return "index";
    }

    @GetMapping(path = {"/valutationpanel"})
    public String getValutationInputPanel(@ModelAttribute ValutationPanelInformation panelInformation, Model model) {
        model.addAttribute("valutationInformation", new ValutationInformation());
        model.addAttribute("pdfResults", new ValutationVisualization());
        model.addAttribute("subject", panelInformation.getSubject());
        model.addAttribute("date", panelInformation.getDate());
        model.addAttribute("academicYear", panelInformation.getAcademicYear());
        model.addAttribute("students", studentService.findByClazzAndAcademicYear(panelInformation.getClazz(), panelInformation.getAcademicYear()));
        return "valutationpanel";
    }

    @GetMapping(path = {"/statspanel"})
    public String getStatsPanel(@ModelAttribute StatsInformation statsInformation, Model model) {
        List<Student> students = studentService.findByClazzAndAcademicYear(statsInformation.getClazz(), statsInformation.getAcademicYear());
        model.addAttribute("students", students);
        model.addAttribute("academicYear", statsInformation.getAcademicYear());
        model.addAttribute("subject", statsInformation.getSubject());
        model.addAttribute("statsForm", new ValutationStatsPanel());
        return "statspanel";
    }
}