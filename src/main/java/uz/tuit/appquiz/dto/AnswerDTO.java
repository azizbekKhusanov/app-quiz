package uz.tuit.appquiz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDTO {

    private Long id;
    private String text;
    private boolean isCorrect;
    private Long questionId;

}
