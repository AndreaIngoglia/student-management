package generatorevoti.controllers;

import com.lowagie.text.DocumentException;
import generatorevoti.database.entities.Valutation;
import generatorevoti.database.entities.ValutationId;
import generatorevoti.services.ValutationPdfGenerator;
import generatorevoti.services.ValutationService;
import generatorevoti.utils.ValutationInformation;
import generatorevoti.utils.ValutationVisualization;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ValutationController {
    private ValutationService valutationService;

    @GetMapping(path = {"/valutations"})
    public List<Valutation> findAll(){
        return valutationService.findAll();
    }

    @PostMapping(path = {"/valutation"})
    public String register(@ModelAttribute ValutationInformation information) {
        valutationService.save(map(information));
        return "success";
    }

    @GetMapping(path = "/pdfvalutations")
    public void generatePdfFile(HttpServletResponse response, @ModelAttribute ValutationVisualization vv) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        ValutationPdfGenerator generator = new ValutationPdfGenerator();
        List<Valutation> valutations = valutationService.findByClazzAndSubjectAndDateAndAcademicYear(vv.getClazz(), vv.getSubject(), vv.getDate(), vv.getAcademicYear());
        generator.generate(valutations, vv.getSubject().toUpperCase(), vv.getClazz().toUpperCase(), vv.getDate().toUpperCase(), response);
    }

    private Valutation map(ValutationInformation input){
        return new Valutation(new ValutationId(input.getDate(), input.getSubject(), input.getEmail()), input.getName(), input.getSurname(), input.getMark(), input.getClazz(), input.getAcademicYear());
    }
}