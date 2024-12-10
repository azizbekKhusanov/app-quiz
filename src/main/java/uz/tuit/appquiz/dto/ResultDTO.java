package uz.tuit.appquiz.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDTO {

    private int correctAnswers;
    private int totalQuestions;
    private int totalScore;
    private int percentage;
}
