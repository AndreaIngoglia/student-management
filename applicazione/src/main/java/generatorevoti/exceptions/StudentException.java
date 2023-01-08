package generatorevoti.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentException extends RuntimeException{
    private final String error;
}