package uz.tuit.appquiz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSubjectDTO {

    @NotBlank
    private String name;
}
