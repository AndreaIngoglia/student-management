package generatorevoti.controllers;

import generatorevoti.database.entities.StudentEntity;
import generatorevoti.services.StudentService;
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

    @GetMapping(path = {"/"})
    public String getMainPage(Model model) {
        model.addAttribute("testInformation", new TestInformation());
        model.addAttribute("statsInformation", new StatsInformation());
        return "index";
    }

    @GetMapping(path = {"/testinputpanel"})
    public String getValutationInputPanel(@ModelAttribute TestInformation testInformation, Model model) {
        model.addAttribute("markFormInput", new MarkFormInput());
        model.addAttribute("pdfResults", new TestResultInformation());
        model.addAttribute("subject", testInformation.getSubject());
        model.addAttribute("date", testInformation.getDate());
        model.addAttribute("students", studentService.findByClazz(testInformation.getClazz()));
        return "valutationpanel";
    }

    @GetMapping(path = {"/studentsstatspanel"})
    public String getStatsPanel(@ModelAttribute StatsInformation statsInformation, Model model) {
        List<StudentEntity> students = studentService.findByClazz(statsInformation.getClazz());
        model.addAttribute("students", students);
        model.addAttribute("subject", statsInformation.getSubject());
        model.addAttribute("statsForm", new StatsVisualization());
        return "statspanel";
    }
}