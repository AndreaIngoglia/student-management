package generatorevoti.controllers;

import generatorevoti.database.entities.Valutation;
import generatorevoti.database.entities.ValutationId;
import generatorevoti.services.ValutationService;
import generatorevoti.utils.ValutationInformation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String register(@ModelAttribute ValutationInformation information){
        valutationService.save(map(information));
        return "success";
    }

    private Valutation map(ValutationInformation input){
        return new Valutation(new ValutationId(input.getDate(), input.getSubject(), input.getEmail()), input.getName(), input.getSurname(), input.getMark());
    }
}
