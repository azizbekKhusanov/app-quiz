package uz.tuit.appquiz.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDTO {

    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private String subjectName;
    private int totalQuestion;
    private List<QuestionDTO> allQuestions = new ArrayList<>();

}