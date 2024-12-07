package uz.tuit.appquiz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    private List<TestDTO> allTests = new ArrayList<>();
}
