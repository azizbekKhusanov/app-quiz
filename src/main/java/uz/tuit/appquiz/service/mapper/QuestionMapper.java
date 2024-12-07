package uz.tuit.appquiz.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.dto.CreateQuestionDTO;
import uz.tuit.appquiz.dto.QuestionDTO;
import uz.tuit.appquiz.entity.Question;
import uz.tuit.appquiz.entity.Test;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final AnswerMapper answerMapper;

    public QuestionDTO convertToDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .text(question.getText())
                .score(question.getScore())
                .testName(question.getTest().getName())
                .choices(question.getAnswers()
                        .stream()
                        .map(answerMapper::convertToDTO)
                        .toList())
                .build();
    }

    public Question convertToEntity(CreateQuestionDTO createQuestionDTO, Test test) {

        return Question.builder()
                .text(createQuestionDTO.getQuestion())
                .score(createQuestionDTO.getScore())
                .answers(createQuestionDTO.getChoices() != null ?
                        createQuestionDTO.getChoices().stream()
                                .map(answerMapper::convertToEntity)
                                .toList() : new ArrayList<>())
                .test(test)
                .build();
    }
}
