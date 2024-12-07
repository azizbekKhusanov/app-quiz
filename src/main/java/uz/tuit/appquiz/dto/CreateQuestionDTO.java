package uz.tuit.appquiz.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuestionDTO {

    private String question;
    private int score;
    private Long testId;
    private List<CreateAnswerDTO> choices;

}
