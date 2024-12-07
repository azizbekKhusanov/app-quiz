package uz.tuit.appquiz.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.dto.AnswerDTO;
import uz.tuit.appquiz.dto.CreateAnswerDTO;
import uz.tuit.appquiz.entity.Answer;
import uz.tuit.appquiz.entity.Question;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.QuestionRepository;

@Component
@RequiredArgsConstructor
public class AnswerMapper {

    private final QuestionRepository questionRepository;

    public AnswerDTO convertToDTO(Answer answer) {
        return AnswerDTO.builder()
                .id(answer.getId())
                .text(answer.getText())
                .isCorrect(answer.isCorrect())
                .questionId(answer.getQuestion().getId())
                .build();
    }

    public Answer convertToEntity(CreateAnswerDTO createAnswerDTO) {
        Question question = questionRepository.findById(createAnswerDTO.getQuestionId())
                .orElseThrow(() -> RestException
                        .restThrow("Question not found !!! "));
        return Answer.builder()
                .text(createAnswerDTO.getAnswer())
                .isCorrect(createAnswerDTO.isCorrect())
                .question(question)
                .build();
    }
}