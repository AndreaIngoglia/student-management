package generatorevoti.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarkFormInput {
    private String name;
    private String surname;
    private String mark;
    private String date;
    private String subject;
    private String email;
    private String clazz;
}