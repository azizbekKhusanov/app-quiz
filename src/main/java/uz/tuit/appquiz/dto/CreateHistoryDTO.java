package uz.tuit.appquiz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateHistoryDTO {

    private int totalScore;
    private Long userId;
    private Long testId;

}
