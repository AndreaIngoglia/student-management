package generatorevoti.controllers;

import com.lowagie.text.DocumentException;
import generatorevoti.database.entities.ValutationEntity;
import generatorevoti.services.StudentStatsGenerator;
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
        List<ValutationEntity> valutations = valutationService.findByEmailAndSubject(statsPanel);
        response.setContentType("application/pdf");
        StudentStatsGenerator generator = new StudentStatsGenerator();
        generator.generate(valutations, statsPanel.getSubject().toUpperCase(), statsPanel.getName().toUpperCase(), statsPanel.getSurname().toUpperCase(), response);
    }
}