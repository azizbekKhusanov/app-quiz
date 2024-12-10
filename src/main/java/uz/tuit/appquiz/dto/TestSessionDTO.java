package uz.tuit.appquiz.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestSessionDTO {

    private Long testId;
    private List<QuestionDTO> questions;

}
