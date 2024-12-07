package uz.tuit.appquiz.dto;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {

    private Long id;
    private String text;
    private int score;
    private String testName;
    private List<AnswerDTO> choices = new ArrayList<>();

}
