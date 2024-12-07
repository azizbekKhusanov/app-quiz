package uz.tuit.appquiz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAnswerDTO {

    private String answer;
    private boolean isCorrect;
    private Long questionId;

}
