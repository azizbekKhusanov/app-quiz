package uz.tuit.appquiz.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.CreateAnswerDTO;
import uz.tuit.appquiz.dto.CreateQuestionDTO;
import uz.tuit.appquiz.dto.QuestionDTO;
import uz.tuit.appquiz.entity.Answer;
import uz.tuit.appquiz.entity.Question;
import uz.tuit.appquiz.entity.Test;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.repository.AnswerRepository;
import uz.tuit.appquiz.repository.QuestionRepository;
import uz.tuit.appquiz.repository.TestRepository;
import uz.tuit.appquiz.service.mapper.QuestionMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;


    @Override
    public ApiResult<QuestionDTO> addQuestionToTest(Long testId, CreateQuestionDTO createQuestionDTO) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found !!!"));

        Question question = questionMapper.convertToEntity(createQuestionDTO, test);
        questionRepository.save(question);

        for (CreateAnswerDTO choice : createQuestionDTO.getChoices()) {
            Answer answer = Answer.builder()
                    .text(choice.getAnswer())
                    .isCorrect(choice.isCorrect())
                    .question(question)
                    .build();
            answerRepository.save(answer);
            question.getAnswers().add(answer);
        }

        return ApiResult.successResponse(questionMapper.convertToDTO(questionRepository.save(question)));
    }

}
