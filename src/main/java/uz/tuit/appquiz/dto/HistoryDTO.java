package uz.tuit.appquiz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryDTO {

    private Long id;
    private int totalScore;
    private String createdAt;
    private String username;
    private String testName;
    private String subjectName;
}
