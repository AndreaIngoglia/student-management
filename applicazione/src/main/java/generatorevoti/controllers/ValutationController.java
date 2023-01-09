package generatorevoti.controllers;

import com.lowagie.text.DocumentException;
import generatorevoti.database.entities.ValutationEntity;
import generatorevoti.database.entities.ValutationId;
import generatorevoti.exceptions.ValutationExpression;
import generatorevoti.services.TestResultsGenerator;
import generatorevoti.services.ValutationService;
import generatorevoti.utils.MarkFormInput;
import generatorevoti.utils.TestResultInformation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ValutationController {
    private ValutationService valutationService;

    @PostMapping(path = {"/valutation"})
    public String register(@ModelAttribute MarkFormInput information) {
        if(valutationService.alreadyExists(new ValutationId(information.getDate(), information.getSubject(), information.getEmail()))){
            throw new ValutationExpression("This mark has already been registered in this system.");
        }
        try{
            Double.parseDouble(information.getMark());
        }catch (NumberFormatException e){
            throw new ValutationExpression("Please, use the '.' (point) character for decimal marks.");
        }
        valutationService.save(map(information));
        return "success";
    }

    @GetMapping(path = "/pdfvalutations")
    public void generatePdfFile(HttpServletResponse response, @ModelAttribute TestResultInformation testInformation) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        TestResultsGenerator generator = new TestResultsGenerator();
        List<ValutationEntity> valutations = valutationService.findByClazzAndSubjectAndDate(testInformation.getClazz(), testInformation.getSubject(), testInformation.getDate());
        generator.generate(valutations, testInformation.getSubject().toUpperCase(), testInformation.getClazz().toUpperCase(), testInformation.getDate().toUpperCase(), response);
    }

    private ValutationEntity map(MarkFormInput input){
        return new ValutationEntity(new ValutationId(input.getDate(), input.getSubject(), input.getEmail()), input.getName(), input.getSurname(), input.getMark(), input.getClazz());
    }
    @ExceptionHandler(ValutationExpression.class)
    public String exceptionHandler(ValutationExpression e){
        return e.getError();
    }
}