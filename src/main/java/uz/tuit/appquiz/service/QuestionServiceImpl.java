package uz.tuit.appquiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.CreateQuestionDTO;
import uz.tuit.appquiz.dto.QuestionDTO;
import uz.tuit.appquiz.entity.Question;
import uz.tuit.appquiz.entity.Test;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.repository.QuestionRepository;
import uz.tuit.appquiz.repository.TestRepository;
import uz.tuit.appquiz.service.mapper.QuestionMapper;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;


    @Override
    public ApiResult<QuestionDTO> addQuestionToTest(Long testId, CreateQuestionDTO createQuestionDTO) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found !!!"));
//
//        Question question = questionMapper.convertToEntity(createQuestionDTO, test);
//        Question save = questionRepository.save(question);
//
//        return ApiResult.successResponse(questionMapper.convertToDTO(save));


        return null;
    }

    private Question addQuestion(CreateQuestionDTO createQuestionDTO, Test test){
        return Question.builder()
                .text(createQuestionDTO.getQuestion())
                .score(createQuestionDTO.getScore())
                .test(test)
                .build();
    }



}
