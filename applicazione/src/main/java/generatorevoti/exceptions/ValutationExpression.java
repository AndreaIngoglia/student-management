package generatorevoti.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValutationExpression extends RuntimeException{
    private String error;
}
