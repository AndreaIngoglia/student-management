package generatorevoti.controllers;

import com.lowagie.text.DocumentException;
import generatorevoti.database.entities.Valutation;
import generatorevoti.services.StatsPdfGenerator;
import generatorevoti.services.ValutationService;
import generatorevoti.utils.StatsVisualization;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class StatsController {
    private ValutationService valutationService;
    @GetMapping(path = "/pdfstats")
    public void generatePdfFile(HttpServletResponse response, @ModelAttribute StatsVisualization statsPanel) throws DocumentException, IOException
    {
        List<Valutation> valutations = valutationService.findByNameAndSurnameAndEmailAndAcademicYearAndClazzAndSubject(statsPanel);
        response.setContentType("application/pdf");
        StatsPdfGenerator generator = new StatsPdfGenerator();
        generator.generate(valutations, statsPanel.getSubject().toUpperCase(), statsPanel.getName().toUpperCase(), statsPanel.getSurname().toUpperCase(), statsPanel.getAcademicYear(), response);
    }
}