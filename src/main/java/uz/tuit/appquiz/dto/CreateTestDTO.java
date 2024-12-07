package uz.tuit.appquiz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTestDTO {

    private String name;
    private String description;
    private Long subjectId;
}